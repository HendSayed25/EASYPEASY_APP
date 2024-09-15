package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentConfirmPhoneNumberBinding
import com.example.eatsygo_app.utils.AuthViewModel

class ConfirmPhoneNumberFragment : Fragment() {

    private var _binding: FragmentConfirmPhoneNumberBinding? = null
    private val binding
        get() = _binding!!

    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var phone: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConfirmPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.phone.observe(viewLifecycleOwner) { phone ->
            binding.mEditPhone.setText(phone)
        }

        binding.verifyPhone.setOnClickListener {
            phone = binding.mEditPhone.text.toString()

            if (isValidPhoneNumber(phone)) {

                authViewModel.setPhone(phone)
                authViewModel.clearSensitiveData()
                Navigation.findNavController(view)
                    .navigate(R.id.action_confirmPhoneNumberFragment_to_otpFragment)

            } else {
                Toast.makeText(context, "Invalid phone number", Toast.LENGTH_LONG).show()
            }
        }

        binding.backPage.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_confirmPhoneNumberFragment_to_registerFragment)
        }

    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // Check if the phone number is null or empty
        if (phoneNumber.isEmpty()) {
            return false
        }

        // Regular expression for validating phone numbers
        val phonePattern = Regex("^[+]?[0-9]{11}$")
        return phonePattern.matches(phoneNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}