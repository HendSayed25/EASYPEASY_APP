package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eatsygo_app.R
import com.example.eatsygo_app.adapter.CartAdapter
import com.example.eatsygo_app.model.CartItem
import com.google.android.material.card.MaterialCardView


class CartFragment : Fragment() {

    lateinit var recyclerView:RecyclerView
    lateinit var cardView:MaterialCardView
    var ListMutableList:MutableList<CartItem>?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_cart)
        cardView=view.findViewById(R.id.card_view)

        ListMutableList= mutableListOf()

       ListMutableList?.add(CartItem(R.drawable.logo,"burger","","","200$"))
        ListMutableList?.add(CartItem(R.drawable.profile_icon,"Pizza","","","500$"))

        recyclerView.adapter=CartAdapter(ListMutableList)
    }

}