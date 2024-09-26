package com.example.eatsygo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FavItemDesignBinding
import com.example.eatsygo_app.model.entity.ProductEntity

class FavAdapter(private var favList: List<ProductEntity>) :
    RecyclerView.Adapter<FavAdapter.FavViewHolder>() {
    class FavViewHolder(
        private var binding: FavItemDesignBinding, private val onItemClicked: OnItemClickFav?
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
                Glide.with(imageView).load(product.image).into(imageView)

                // Handle clicks
                addToFavDesign.setOnClickListener {
                    onItemClicked?.addToFav(product)
                }
                addToCartFav.setOnClickListener {
                    onItemClicked?.addToCart(product)
                }

                root.setOnClickListener {
                    onItemClicked?.onItemDetails(product)
                }


            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding =
            FavItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding, onItemClicked)
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(favList.get(position))


    }

    fun updateData(ProductList: List<ProductEntity>) {
        favList = ProductList
        notifyDataSetChanged()
    }


    var onItemClicked: OnItemClickFav? = null

    // Interface to handle click events
    interface OnItemClickFav {
        fun onItemDetails(productItem: ProductEntity)
        fun addToFav(productItem: ProductEntity)
        fun addToCart(productItem: ProductEntity)
    }

}