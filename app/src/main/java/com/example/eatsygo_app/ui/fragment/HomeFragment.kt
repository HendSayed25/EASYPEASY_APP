package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eatsygo_app.R
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


        binding.catOne.setOnClickListener {
            binding.catOneTxt.visibility = View.VISIBLE
            binding.catTwoTxt.visibility = View.GONE
            binding.catThreeTxt.visibility = View.GONE
            binding.catFourTxt.visibility = View.GONE
            binding.catOneTxt.text = "Men's Clothes"
            binding.catOne.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.beige
                )
            )
            //get the man category from database
            CoroutineScope(Dispatchers.IO).launch {
                // Switch back to the main thread to update the UI
                withContext(Dispatchers.Main) {
                    // Perform your UI updates here, like setting data in a RecyclerView or TextView
                    var list=ClotheDatabase.getInstance(requireContext()).productDao().getProductByCategory("men's clothing")
                    productAdapter.updateData(list)

                }
            }
        }

        binding.catTwo.setOnClickListener {
            binding.catTwoTxt.visibility = View.VISIBLE
            binding.catOneTxt.visibility = View.GONE
            binding.catThreeTxt.visibility = View.GONE
            binding.catFourTxt.visibility = View.GONE
            binding.catTwoTxt.text = "Women's Clothes"
            binding.catTwo.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.beige
                )
            )

            //get the man category from database
            CoroutineScope(Dispatchers.IO).launch {
                // Switch back to the main thread to update the UI
                withContext(Dispatchers.Main) {
                    // Perform your UI updates here, like setting data in a RecyclerView or TextView
                    var list=ClotheDatabase.getInstance(requireContext()).productDao().getProductByCategory("women's clothing")
                    productAdapter.updateData(list)

                }

            }
        }

        binding.catThree.setOnClickListener {
            binding.catThreeTxt.visibility = View.VISIBLE
            binding.catOneTxt.visibility = View.GONE
            binding.catTwoTxt.visibility = View.GONE
            binding.catFourTxt.visibility = View.GONE
            binding.catThreeTxt.text = "electronics"
            binding.catThree.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.beige
                )
            )
            //get the man category from database
            CoroutineScope(Dispatchers.IO).launch {
                // Switch back to the main thread to update the UI
                withContext(Dispatchers.Main) {
                    // Perform your UI updates here, like setting data in a RecyclerView or TextView
                    var list=ClotheDatabase.getInstance(requireContext()).productDao().getProductByCategory("electronics")
                    productAdapter.updateData(list)

                }

            }
        }

        binding.catFour.setOnClickListener {
            binding.catFourTxt.visibility = View.VISIBLE
            binding.catOneTxt.visibility = View.GONE
            binding.catTwoTxt.visibility = View.GONE
            binding.catThreeTxt.visibility = View.GONE
            binding.catFourTxt.text = "jewelery"
            binding.catFour.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.beige
                )
            )
            //get the man category from database
            CoroutineScope(Dispatchers.IO).launch {
                // Switch back to the main thread to update the UI
                withContext(Dispatchers.Main) {
                    // Perform your UI updates here, like setting data in a RecyclerView or TextView
                    var list=ClotheDatabase.getInstance(requireContext()).productDao().getProductByCategory("jewelery")
                    productAdapter.updateData(list)

                }

            }
        }


      onItemClick()

    }

    fun onItemClick(){

        productAdapter.onItemClicked=object:ProductAdapter.OnItemClick {
            override fun onItemDetails(productItem: ProductEntity) {
                //open new fragment to show details and add to cart
                openItemDetailFragment(productItem.id)
            }

            override fun addToFav(productItem: ProductEntity) {
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main){
                        productItem.isFavourite=true
                        ClotheDatabase.getInstance(requireContext()).productDao().updateProduct(productItem)
                        productAdapter.notifyDataSetChanged()
                        Log.e("fav",""+productItem.isFavourite)
                    }
                }

            }

        }
    }

    private fun openItemDetailFragment(itemId: Int) {
        val fragment = ProductDetailsFragment.newInstance(itemId)

        // Perform fragment transaction to replace the current fragment with the detail fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}