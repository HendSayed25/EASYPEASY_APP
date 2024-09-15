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
import com.example.eatsygo_app.databinding.FragmentForgetPasswordBinding
import com.example.eatsygo_app.utils.AuthViewModel
import com.example.eatsygo_app.utils.SharedPrefsManager

class ForgetPasswordFragment : Fragment() {

    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var email: String
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.email.observe(viewLifecycleOwner) { email ->
            binding.mEditEmail.setText(email)
        }

        binding.ForgetPasswordBtn.setOnClickListener {

            email = binding.mEditEmail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(context, "Email cannot be empty.", Toast.LENGTH_LONG).show()
            } else if (email != SharedPrefsManager.getValue("email")) {
                Toast.makeText(context, "Invalid Email.", Toast.LENGTH_LONG).show()
            } else {
                binding.passwordViewTv.visibility = View.VISIBLE
                binding.passwordViewTv.text = SharedPrefsManager.getValue("password")
            }

        }

        binding.backPage.setOnClickListener {
            Navigation.findNavController(view).navigate(
                R.id.action_forgetPasswordFragment_to_loginFragment
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}