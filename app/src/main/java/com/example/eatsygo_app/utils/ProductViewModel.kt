package com.example.eatsygo_app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatsygo_app.model.entity.ProductEntity
import com.example.eatsygo_app.source.local.ClotheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val clotheRepository: ClotheRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> get() = _products

    fun fetchProducts() {
        viewModelScope.launch {
            clotheRepository.fetchAndSaveProducts()
            loadProductsFromDatabase()
        }
    }

    private fun loadProductsFromDatabase() {
        viewModelScope.launch {
            val productList = clotheRepository.getAllProducts()
            _products.postValue(productList)
        }
    }
}
