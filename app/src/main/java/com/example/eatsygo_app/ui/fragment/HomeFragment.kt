package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.eatsygo_app.R
import com.example.eatsygo_app.adapter.ProductAdapter
import com.example.eatsygo_app.databinding.FragmentHomeBinding
import com.example.eatsygo_app.utils.ProductViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var productAdapter:ProductAdapter
    private lateinit var productViewModel:ProductViewModel
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Initialize ViewModel
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        // Initialize Adapter
        productAdapter = ProductAdapter(emptyList())
        binding.recyclerCart.adapter = productAdapter

        // Observe the products LiveData
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            productAdapter.updateData(products)
        }

        // Fetch products
        productViewModel.fetchProducts()

        return binding.root
    }


    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.catOne.setOnClickListener { //check other tabes
            binding.catOneTxt.visibility = View.VISIBLE
            binding.catOneTxt.text = "Man's Clothes"
            binding.catOne.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gold
                )
            )
        }



//        // Observe the LiveData from ViewModel
//        productViewModel.products.observe(viewLifecycleOwner) { products ->
//            // Update UI, e.g., set adapter for RecyclerView
//            val productAdapter = ProductAdapter(products)
//            binding.recyclerCart.adapter = productAdapter
//        }
//
//        // Fetch products when needed
//        productViewModel.fetchProducts()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}