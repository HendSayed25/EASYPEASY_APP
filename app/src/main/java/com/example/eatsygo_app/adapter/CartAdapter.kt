package com.example.eatsygo_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eatsygo_app.R
import com.example.eatsygo_app.model.CartItem

class CartAdapter(var items:MutableList<CartItem>?): RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage:ImageView=itemView.findViewById(R.id.item_image)
        var itemName:TextView=itemView.findViewById(R.id.item_name)
        var itemCountTv:TextView=itemView.findViewById(R.id.item_count_tv)
        var numberOfItemNeeded:TextView=itemView.findViewById(R.id.numberOfItemNeeded)
        var itemPrice:TextView=itemView.findViewById(R.id.item_price)
        var plusIcon:ImageView=itemView.findViewById(R.id.plus_icon)
        var minusIcon:ImageView=itemView.findViewById(R.id.minus_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.cart_item_design,parent,false)
        return CartItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        var item=items!!.get(position)
        holder.itemName.text=item.itemName
        holder.itemPrice.text=item.itemPrice
        holder.itemCountTv.text= item.itemCountTv.toString()
        holder.numberOfItemNeeded.text=item.numberOfItemNeeded.toString()
      //  holder.plusIcon.setImageResource(R.drawable.plus)
     //   holder.minusIcon.setImageResource(R.drawable.minus)

        //holder.itemImage.setImageResource(R.) ??

    }


}