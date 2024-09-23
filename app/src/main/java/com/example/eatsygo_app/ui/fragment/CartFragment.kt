package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eatsygo_app.R
import com.example.eatsygo_app.adapter.CartAdapter
import com.example.eatsygo_app.adapter.ProductAdapter
import com.example.eatsygo_app.databinding.FragmentCartBinding
import com.example.eatsygo_app.model.CartItem
import com.example.eatsygo_app.model.Rating
import com.example.eatsygo_app.model.entity.ProductEntity
import com.example.eatsygo_app.source.local.ClotheDatabase
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment : Fragment() {
    private var _binding:FragmentCartBinding?=null
    private val binding
        get() = _binding!! //this line to when close the fragment all data which related to it release from memory when i destroy it in the end of code

    lateinit var cartList:MutableList<CartItem>
    lateinit var cartAdapter:CartAdapter
    var countOfItem=0


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

        cartList= mutableListOf()
        cartAdapter=CartAdapter(cartList)

        binding.recyclerCart.adapter=cartAdapter

        getCartItemFromDatabase()

        plusOrMinusClick()



        binding.btnPlaceOrder.setOnClickListener {
            placeOrderButtonSheet()
        }

    }

    private fun placeOrderButtonSheet()
    {
        val placeButtonSheet=PlaceOrderFragment()
        fragmentManager?.let { placeButtonSheet.show(it,"") }
    }

    private fun getCartItemFromDatabase(){

        //dispatcher is optimized for tasks like reading from or writing to a database or making network calls—operations that don't require heavy CPU usage
        //dispatcher.IO to avoid blocking the main UI thread.
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                var list=ClotheDatabase.getInstance(requireContext()).productDao().getCartProduct()
                //convert productEntity list to cartItemList
                for(i :Int in 0 until list.size){
                    cartList.add(CartItem(list.get(i).price.toString(),1,list.get(i).title,list.get(i).price,list.get(i).image,list.get(i).rating,list.get(i).isFavourite,list.get(i).isCartIn))
                }
                //handel the UI
                if (cartList.size>0){
                    binding.recyclerCart.visibility=View.VISIBLE
                    binding.recyclerEmpty.visibility=View.INVISIBLE
                }else{
                    binding.recyclerEmpty.visibility=View.VISIBLE
                    binding.recyclerCart.visibility=View.INVISIBLE
                }
                //update the adapter list
                cartAdapter.updateData(cartList.toList())
            }
        }
    }
    private fun plusOrMinusClick(){
        cartAdapter.onItemClicked=object:CartAdapter.onItemClick{
            override fun plusItemClick(cartItem: CartItem) {
                countOfItem++
                cartItem.numberOfItemNeeded=countOfItem
                cartItem.itemCountTv= "${countOfItem} * ${cartItem.price}"


                // Notify the adapter that the data for this item has changed
                val position = cartList.indexOf(cartItem)
                cartAdapter.notifyItemChanged(position)

            }

            override fun minsItemClick(cartItem: CartItem) {
                countOfItem--
                if(countOfItem<0)countOfItem=0
                cartItem.numberOfItemNeeded=countOfItem
                cartItem.itemCountTv= "${countOfItem} * ${cartItem.price}"
                    //(countOfItem*(cartItem.price)).toString()

                // Notify the adapter that the data for this item has changed
                val position = cartList.indexOf(cartItem)
                cartAdapter.notifyItemChanged(position)

            }
        }
    }
    // leak Memory
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}