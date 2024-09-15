package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eatsygo_app.R
import com.example.eatsygo_app.adapter.CartAdapter
import com.example.eatsygo_app.databinding.FragmentCartBinding
import com.example.eatsygo_app.model.CartItem
import com.google.android.material.card.MaterialCardView


class CartFragment : Fragment() {
    private var _binding:FragmentCartBinding?=null
    private val binding
        get() = _binding!! //this line to when close the fragment all data which related to it release from memory when i destroy it in the end of code

    var ListMutableList:MutableList<CartItem>?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ListMutableList= mutableListOf()

       // ListMutableList?.add(CartItem(R.drawable.logo,"burger","","","200$"))
       // ListMutableList?.add(CartItem(R.drawable.profile_icon,"Pizza","","","500$"))

        binding.recyclerCart.adapter=CartAdapter(ListMutableList)

        binding.btnPlaceOrder.setOnClickListener {
            placeOrderButtonSheet()

        }






    }

    private fun placeOrderButtonSheet()
    {
        val placeButtonSheet=PlaceOrderFragment()
        fragmentManager?.let { placeButtonSheet.show(it,"") }


    }
    // leak Memory
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}