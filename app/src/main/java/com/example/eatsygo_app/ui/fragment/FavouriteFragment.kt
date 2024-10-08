package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.eatsygo_app.adapter.FavAdapter
import com.example.eatsygo_app.databinding.FragmentFavouriteBinding
import com.example.eatsygo_app.model.entity.ProductEntity
import com.example.eatsygo_app.source.local.ClotheDatabase
import com.example.eatsygo_app.utils.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    lateinit var favList: List<ProductEntity>
    lateinit var productAdapter: FavAdapter

    // Hilt will provide the ViewModel
    private val productViewModel: ProductViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter
        favList = listOf()
        productAdapter = FavAdapter(emptyList())
        binding.recyclerFav.adapter = productAdapter


        //get All fav product from database
        getFavProduct()

        // Handle item click events
        onFavClick()

    }

    private fun onFavClick() {

        productAdapter.onItemClicked = object : FavAdapter.OnItemClickFav {
            override fun onItemDetails(productItem: ProductEntity) {

                val direction =
                    FavouriteFragmentDirections.actionFavouriteFragmentToProductDetailsFragment(
                        productItem
                    )

                findNavController().navigate(direction)

            }

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

            override fun addToCart(productItem: ProductEntity) {
                CoroutineScope(Dispatchers.IO).launch {
                    productItem.isCartIn = true
                    withContext(Dispatchers.Main) {
                        ClotheDatabase.getInstance(requireContext()).productDao()
                            .updateProduct(productItem)
                        productAdapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }


    private fun getFavProduct() {
        productViewModel.viewModelScope.launch {
            favList =
                ClotheDatabase.getInstance(requireContext()).productDao().getFavouriteProduct()
            if (favList.isEmpty()) {
                binding.emptyFavList.visibility = View.VISIBLE
                binding.recyclerFav.visibility = View.INVISIBLE
            } else {
                binding.recyclerFav.visibility = View.VISIBLE
                binding.emptyFavList.visibility = View.INVISIBLE
            }
            productAdapter.updateData(favList)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
