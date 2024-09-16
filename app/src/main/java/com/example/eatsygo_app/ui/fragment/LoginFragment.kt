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
import com.example.eatsygo_app.databinding.FragmentLoginBinding
import com.example.eatsygo_app.utils.AuthViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var name: String
    private lateinit var password: String
    private lateinit var authResult: Pair<Boolean, String>

    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.email.observe(viewLifecycleOwner) { email ->
            binding.mEditEmail.setText(email)
        }

        authViewModel.password.observe(viewLifecycleOwner) { password ->
            binding.mEditPassword.setText(password)
        }

        binding.LoginBtn.setOnClickListener {

            name = binding.mEditEmail.text.toString()
            password = binding.mEditPassword.text.toString()
            authResult = validateLoginData(name, password)

            if (authResult.first) {
                authViewModel.setLoginState(true)
                Toast.makeText(context, authResult.second, Toast.LENGTH_LONG).show()
                authViewModel.clearSensitiveData()
                Navigation.findNavController(view).navigate(
                    R.id.action_loginFragment_to_mainActivity
                )
            }
        }

        binding.forgetTv.setOnClickListener {
            Navigation.findNavController(view).navigate(
                R.id.action_loginFragment_to_forgetPasswordFragment
            )
        }

        binding.noAccountTv.setOnClickListener {
            Navigation.findNavController(view).navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }

    }

    private fun validateLoginData(
        email: String, password: String
    ): Pair<Boolean, String> {
        // Check if email is empty
        if (email.isEmpty()) {
            binding.mEditEmail.error = "Email cannot be empty."
            return Pair(false, "Email cannot be empty.")
        }

        // Check if password is empty
        if (password.isEmpty()) {
            binding.mEditPassword.error = "Password cannot be empty."
            return Pair(false, "Password cannot be empty.")
        }

        // Validation for the email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Invalid email format.", Toast.LENGTH_LONG).show()
            return Pair(false, "Invalid email format.")
        }

        if (!authViewModel.checkLogin(email, password)) {
            Toast.makeText(context, "Invalid email or password.", Toast.LENGTH_LONG).show()
            return Pair(false, "Invalid email or password.")
        }

        // If all checks passed
        return Pair(true, "Login data is valid.")
    }

    // leak Memory
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}