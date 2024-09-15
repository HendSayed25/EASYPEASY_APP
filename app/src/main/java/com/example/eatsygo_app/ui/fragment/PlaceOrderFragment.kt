package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentCartBinding
import com.example.eatsygo_app.databinding.FragmentPlaceOrderBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PlaceOrderFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPlaceOrderBinding?=null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentPlaceOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlaceOrder.setOnClickListener {
            if(Validate()){
                Toast.makeText(requireContext(),"Payment Successfully..",Toast.LENGTH_LONG).show()
            }
        }


    }
    private fun Validate():Boolean
    {
        var isValid=true
        if(binding.cardNumber.text.toString().isBlank())
        {
            binding.cardNumber.error="Please Enter Card Number"
            isValid=false
        }

        if(binding.MMYY.editableText.toString().isBlank())
        {
            binding.MMYY.error="Please Enter MM of Card"
            isValid=false
        }

        if(binding.CVC.editableText.toString().isBlank())
        {
            binding.CVC.error="Please Enter MM of Card"
            isValid=false
        }

        return isValid
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}