package com.example.eatsygo_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentProductDetailsBinding
import com.example.eatsygo_app.model.entity.ProductEntity
import com.example.eatsygo_app.source.local.ClotheDatabase
import com.example.eatsygo_app.ui.activity.MainActivity
import com.example.eatsygo_app.utils.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private lateinit var productViewModel: ProductViewModel

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideBottomNavigation()

        val myData = arguments?.getParcelable<ProductEntity>("productItem")
        myData?.let {
            // Use the data
            Glide.with(binding.imageView).load(it.image).placeholder(R.drawable.placeholder)
                .into(binding.imageView)
            binding.title.text = it.title
            binding.description.text = it.description
            binding.materialRatingBar.rating = it.rating.rate
            binding.countRating.text = "(${it.rating.count})"
            binding.price.text = it.price.toString()
            binding.addToFav.setImageResource(
                if (it.isFavourite) R.drawable.fav_red
                else R.drawable.fav_icon
            )
        }

        binding.backPage.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        binding.addToFav.setOnClickListener {
            myData!!.isFavourite = !myData.isFavourite
            binding.addToFav.setImageResource(
                if (myData.isFavourite) R.drawable.fav_red
                else R.drawable.fav_icon
            )
            addToFav(myData)
        }

        //add item to cart
        binding.addToCartBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    //update the database
                    myData!!.isCartIn = true
                    ClotheDatabase.getInstance(requireContext()).productDao()
                        .updateProduct(myData)
                }
            }
        }

    }

    //add item in fav
    private fun addToFav(productItem: ProductEntity) {
        CoroutineScope(Dispatchers.IO).launch {

            ClotheDatabase.getInstance(requireContext()).productDao().updateProduct(productItem)
            // Update the LiveData with the updated list
            withContext(Dispatchers.Main) {
                productViewModel.updateProduct(productItem)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as MainActivity).showBottomNavigation()
    }
}