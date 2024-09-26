package com.example.eatsygo_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.eatsygo_app.databinding.FragmentPlaceOrderBinding
import com.example.eatsygo_app.utils.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlaceOrderFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPlaceOrderBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve order total from arguments
        val orderTotal = arguments?.getDouble("orderTotal") ?: 0.0
        setData(orderTotal)

        binding.btnPlaceOrder.setOnClickListener {
            if (validate()) {
                authViewModel.addPoint()
                Toast.makeText(requireContext(), "Payment Successfully..", Toast.LENGTH_LONG).show()
                dismiss() // Optionally dismiss the dialog after payment
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setData(orderTotal: Double) {
        binding.totalPriceVisa.text = "${orderTotal}$" // Set total price
    }

    private fun validate(): Boolean {
        var isValid = true
        if (binding.cardNumber.text.toString().isBlank()) {
            binding.cardNumber.error = "Please Enter Card Number"
            isValid = false
        }

        if (binding.MMYY.editableText.toString().isBlank()) {
            binding.MMYY.error = "Please Enter MM of Card"
            isValid = false
        }

        if (binding.CVC.editableText.toString().isBlank()) {
            binding.CVC.error = "Please Enter CVC of Card"
            isValid = false
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(orderTotal: Double): PlaceOrderFragment {
            val fragment = PlaceOrderFragment()
            val args = Bundle().apply {
                putDouble("orderTotal", orderTotal)
            }
            fragment.arguments = args
            return fragment
        }
    }
}