package com.example.eatsygo_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.CartItemDesignBinding
import com.example.eatsygo_app.databinding.FavItemDesignBinding
import com.example.eatsygo_app.model.CartItem
import com.example.eatsygo_app.model.entity.ProductEntity

class CartAdapter(private var cartList:List<CartItem>): RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(private var binding:CartItemDesignBinding,private var onItemClick: onItemClick?):RecyclerView.ViewHolder(binding.root){

        fun bind(product:CartItem){//delete it and be default 1* price ??????
            binding.apply {
                itemName.text=product.title
                itemPrice.text=product.price.toString()
                numberOfItemNeeded.text=product.numberOfItemNeeded.toString()
                itemCountTv.text=product.itemCountTv
                Glide.with(itemImage).load(product.image).into(itemImage)

                //call back
                plusIcon.setOnClickListener {
                    onItemClick?.plusItemClick(product)
                }

                minusIcon.setOnClickListener {
                    onItemClick?.minsItemClick(product)

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding =CartItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartItemViewHolder(binding,onItemClicked)
    }

    override fun getItemCount(): Int=cartList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        var item=cartList.get(position)
        holder.bind(item)


    }

    fun updateData(CartList: List<CartItem>) {
        cartList = CartList
        notifyDataSetChanged()
    }

    var onItemClicked:onItemClick?=null

    interface onItemClick{
        fun plusItemClick(cartItem: CartItem)
        fun minsItemClick(cartItem: CartItem)
    }


}