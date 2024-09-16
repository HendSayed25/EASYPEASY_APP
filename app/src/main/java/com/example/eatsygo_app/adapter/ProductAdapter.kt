package com.example.eatsygo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatsygo_app.databinding.ProductItemBinding
import com.example.eatsygo_app.model.entity.ProductEntity


class ProductAdapter(private var productList: List<ProductEntity>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity) {
            binding.apply {
                // Bind product data to UI components (using ViewBinding)
                binding.title.text = product.title
                binding.price.text = "$${product.price}"
                binding.description.text = product.description
                Glide.with(binding.imageView).load(product.image).into(binding.imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newProductList: List<ProductEntity>) {
        productList = newProductList
        notifyDataSetChanged() // Notify the RecyclerView that the data has changed
    }
}
