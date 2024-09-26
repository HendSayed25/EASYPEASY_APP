package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eatsygo_app.adapter.CartAdapter
import com.example.eatsygo_app.databinding.FragmentCartBinding
import com.example.eatsygo_app.model.CartData
import com.example.eatsygo_app.source.local.ClotheDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartList: MutableList<CartData>
    private lateinit var cartAdapter: CartAdapter
    private var subTotal = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartList = mutableListOf()
        cartAdapter = CartAdapter(cartList)

        binding.recyclerCart.adapter = cartAdapter

        getCartItemFromDatabase()

        binding.btnPlaceOrder.setOnClickListener {
            showPlaceOrderSheet()
        }
        binding.btnApplyCode.setOnClickListener {
            subTotal -= (subTotal * .2)
            updateUI()
        }
    }

    private fun showPlaceOrderSheet() {
        val placeOrderFragment = PlaceOrderFragment.newInstance(subTotal + 20.0)
        placeOrderFragment.show(parentFragmentManager, placeOrderFragment.tag)
    }

    private fun getCartItemFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = ClotheDatabase.getInstance(requireContext()).productDao().getCartProduct()
            for (item in list) {
                cartList.add(
                    CartData(
                        id = item.id,
                        image = item.image,
                        price = item.price,
                        title = item.title,
                        itemCount = item.itemCount + 1,
                        isCartIn = true
                    )
                )
                subTotal += (item.price * (item.itemCount + 1))
            }
            withContext(Dispatchers.Main) {
                updateUI()
            }
        }
    }

    private fun updateUI() {
        if (cartList.isNotEmpty()) {
            binding.recyclerCart.visibility = View.VISIBLE
            binding.recyclerEmpty.visibility = View.INVISIBLE
        } else {
            binding.recyclerEmpty.visibility = View.VISIBLE
            binding.recyclerCart.visibility = View.INVISIBLE
        }

        cartAdapter.updateData(cartList)
        binding.subTotalValue.text = String.format("%.2f", subTotal)
        binding.totalValue.text = String.format("%.2f", subTotal + 20.0) // Including shipping
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}