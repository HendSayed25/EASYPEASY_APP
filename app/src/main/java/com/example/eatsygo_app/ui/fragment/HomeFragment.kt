package com.example.eatsygo_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eatsygo_app.adapter.ProductAdapter
import com.example.eatsygo_app.databinding.FragmentHomeBinding
import com.example.eatsygo_app.model.entity.ProductEntity
import com.example.eatsygo_app.source.local.ClotheDatabase
import com.example.eatsygo_app.utils.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var productAdapter: ProductAdapter
    private lateinit var productViewModel: ProductViewModel
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Initialize ViewModel
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // Initialize Adapter
        productAdapter = ProductAdapter(emptyList())
        binding.recyclerCart.adapter = productAdapter

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel.products.observe(viewLifecycleOwner) { products ->
            productAdapter.updateData(products)
        }

        productViewModel.fetchProducts()


        binding.catOne.setOnClickListener {
            binding.catOneTxt.visibility = View.VISIBLE
            binding.catTwoTxt.visibility = View.GONE
            binding.catThreeTxt.visibility = View.GONE
            binding.catFourTxt.visibility = View.GONE

            getDataByCategory("men's clothing")
        }

        binding.catTwo.setOnClickListener {
            binding.catTwoTxt.visibility = View.VISIBLE
            binding.catOneTxt.visibility = View.GONE
            binding.catThreeTxt.visibility = View.GONE
            binding.catFourTxt.visibility = View.GONE

            getDataByCategory("women's clothing")
        }

        binding.catThree.setOnClickListener {
            binding.catThreeTxt.visibility = View.VISIBLE
            binding.catOneTxt.visibility = View.GONE
            binding.catTwoTxt.visibility = View.GONE
            binding.catFourTxt.visibility = View.GONE

            getDataByCategory("electronics")
        }

        binding.catFour.setOnClickListener {
            binding.catFourTxt.visibility = View.VISIBLE
            binding.catOneTxt.visibility = View.GONE
            binding.catTwoTxt.visibility = View.GONE
            binding.catThreeTxt.visibility = View.GONE

            getDataByCategory("jewelery")
        }


        onItemClick()

    }

    private fun getDataByCategory(category: String) {
        //get the man category from database
        CoroutineScope(Dispatchers.IO).launch {
            // Switch back to the main thread to update the UI
            withContext(Dispatchers.Main) {
                // Perform your UI updates here, like setting data in a RecyclerView or TextView
                val list = ClotheDatabase.getInstance(requireContext()).productDao()
                    .getProductByCategory(category = category)
                productAdapter.updateData(list)
            }
        }
    }

    private fun onItemClick() {

        productAdapter.onItemClicked = object : ProductAdapter.OnItemClick {
            override fun onItemDetails(productItem: ProductEntity) {
                //open new fragment to show details and add to cart

                val direction =
                    HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(productItem)

                findNavController().navigate(direction)

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun addToFav(productItem: ProductEntity) {
                CoroutineScope(Dispatchers.IO).launch {

                    productItem.isFavourite = !productItem.isFavourite

                    ClotheDatabase.getInstance(requireContext()).productDao()
                        .updateProduct(productItem)
                    // Update the LiveData with the updated list
                    withContext(Dispatchers.Main) {
                        productViewModel.updateProduct(productItem)
                        productAdapter.notifyDataSetChanged()
                    }
                }

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}