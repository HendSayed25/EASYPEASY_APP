package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.example.eatsygo_app.R
import com.example.eatsygo_app.adapter.ProductAdapter
import com.example.eatsygo_app.databinding.FragmentHomeBinding
import com.example.eatsygo_app.databinding.FragmentProductDeatailsBinding
import com.example.eatsygo_app.model.entity.ProductEntity
import com.example.eatsygo_app.source.local.ClotheDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductDetailsFragment : Fragment() {
    companion object {
        private const val ITEM_ID = "item_id"
        fun newInstance(itemId: Int): ProductDetailsFragment {
            val fragment = ProductDetailsFragment()
            val args = Bundle().apply {
                putInt(ITEM_ID, itemId) // Passing the item ID to the fragment
            }
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentProductDeatailsBinding? = null
    private lateinit var productItem:ProductEntity
    var ProductID:Int=0
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Retrieve the passed ID
        ProductID = arguments?.getInt(ITEM_ID) ?: 0

        // Inflate the layout for this fragment
        _binding = FragmentProductDeatailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

          //put data into fields
        CoroutineScope(Dispatchers.IO).launch {
            // Switch back to the main thread to update the UI
            withContext(Dispatchers.Main) {
                //get the data of product item i received
                productItem=ClotheDatabase.getInstance(requireContext()).productDao().getProductById(ProductID)
                //put data into fields
                Glide.with(binding.imageView).load(productItem.image).into(binding.imageView)
                binding.price.text=productItem.price.toString()
                binding.description.text=productItem.description
                binding.title.text=productItem.title
                if(productItem.isFavourite==true){
                  binding.addToFavRed.visibility=View.VISIBLE
                  binding.addToFav.visibility=View.GONE
                }else{
                    binding.addToFavRed.visibility=View.GONE
                    binding.addToFav.visibility=View.VISIBLE
                }
            }
        }

        //add item in fav
        binding.addToFav.setOnClickListener {
            binding.addToFav.visibility = View.GONE
            binding.addToFavRed.visibility = View.VISIBLE

            //apply animation in logo
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.blink_animation)
            binding.addToFavRed.startAnimation(animation)

            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    productItem.isFavourite = true
                    ClotheDatabase.getInstance(requireContext()).productDao().updateProduct(productItem)
                }
            }
        }

            //add item to cart
            binding.addToCartBtn.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        //update the database
                        productItem.isCartIn = true
                        ClotheDatabase.getInstance(requireContext()).productDao().updateProduct(productItem)
                    }
                }
            }

        }
}