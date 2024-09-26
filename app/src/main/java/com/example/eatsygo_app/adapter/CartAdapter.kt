package com.example.eatsygo_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatsygo_app.databinding.CartItemDesignBinding
import com.example.eatsygo_app.model.CartData
import com.example.eatsygo_app.source.local.ClotheDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(
    private var cartList: List<CartData>
) : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(
        private var binding: CartItemDesignBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: CartData) {
            binding.apply {
                itemName.text = product.title
                numberOfItemNeeded.text = product.itemCount.toString()
                itemPrice.text = (product.price * product.itemCount).toString()
                itemCountTv.text = "${numberOfItemNeeded.text} * ${product.price}"
                Glide.with(itemImage).load(product.image).into(itemImage)

                // Update subtotal on count change
                val updateSubtotal = { newCount: Int ->
                    val newPrice = product.price * newCount
                    itemPrice.text = newPrice.toString()
                }

                minusIcon.setOnClickListener {
                    if (product.itemCount > 1) {
                        product.itemCount--
                        updateSubtotal(product.itemCount)
                        numberOfItemNeeded.text = product.itemCount.toString()
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            ClotheDatabase.getInstance(binding.root.context).productDao()
                                .removeCartProduct(product.id)
                        }
                    }
                }

                plusIcon.setOnClickListener {
                    product.itemCount++
                    updateSubtotal(product.itemCount)
                    numberOfItemNeeded.text = product.itemCount.toString()
                    CoroutineScope(Dispatchers.IO).launch {
                        ClotheDatabase.getInstance(binding.root.context).productDao()
                            .updateCartProduct(product.id, product.itemCount)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding =
            CartItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val item = cartList[position]
        holder.bind(item)
    }

    fun updateData(cartList: List<CartData>) {
        this.cartList = cartList
        notifyDataSetChanged()
    }
}