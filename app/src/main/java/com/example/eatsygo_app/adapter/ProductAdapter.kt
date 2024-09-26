package com.example.eatsygo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.ProductItemBinding
import com.example.eatsygo_app.model.entity.ProductEntity

class ProductAdapter(private var productList: List<ProductEntity>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ProductItemBinding, private val onItemClicked: OnItemClick?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity) {
            binding.apply {
                // Bind product data to UI components
                title.text = product.title
                price.text = "$${product.price}"
                description.text = product.description
                addToFavDesign.setImageResource(
                    if (product.isFavourite) R.drawable.fav_red
                    else R.drawable.fav_icon
                ) // Initial state for fav icon

                materialRatingBar.rating = product.rating.rate

                Glide.with(imageView)
                    .load(product.image)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView)

                // Handle click event for the addToFav icon
                addToFavDesign.setOnClickListener {
                    onItemClicked?.addToFav(product)
                }

                // Handle click event for the entire item (for item details)
                root.setOnClickListener {
                    onItemClicked?.onItemDetails(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newProductList: List<ProductEntity>) {
        productList = newProductList
        notifyDataSetChanged()
    }

    var onItemClicked: OnItemClick? = null

    // Interface to handle click events
    interface OnItemClick {
        fun onItemDetails(productItem: ProductEntity)
        fun addToFav(productItem: ProductEntity)
    }
}
