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
import com.example.eatsygo_app.databinding.FragmentRegisterBinding
import com.example.eatsygo_app.utils.AuthViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var firstname: String
    private lateinit var lastname: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private lateinit var registerResult: Pair<Boolean, String>

    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.firstname.observe(viewLifecycleOwner) { firstname ->
            binding.firstnameEdt.setText(firstname)
        }
        authViewModel.lastname.observe(viewLifecycleOwner) { lastname ->
            binding.lastnameEdt.setText(lastname)
        }
        authViewModel.email.observe(viewLifecycleOwner) { email ->
            binding.mEditEmail.setText(email)
        }
        authViewModel.password.observe(viewLifecycleOwner) { password ->
            binding.passwordInputEditText.setText(password)
        }

        binding.RegisterBtn.setOnClickListener {

            firstname = binding.firstnameEdt.text.toString()
            lastname = binding.lastnameEdt.text.toString()
            email = binding.mEditEmail.text.toString()
            password = binding.passwordInputEditText.text.toString()
            confirmPassword = binding.confirmPasswordInputEdt.text.toString()

            registerResult = validateRegistrationData(
                firstname, lastname, email, password, confirmPassword
            )

            if (registerResult.first) {

                authViewModel.setUserName(firstname, lastname)
                authViewModel.setEmail(email)
                authViewModel.setPassword(password)

                Toast.makeText(context, registerResult.second, Toast.LENGTH_LONG).show()
                authViewModel.clearSensitiveData()
                Navigation.findNavController(view).navigate(
                    R.id.action_registerFragment_to_confirmPhoneNumberFragment
                )
            } else {
                Toast.makeText(context, registerResult.second, Toast.LENGTH_LONG).show()
            }

        }

        binding.backPage.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

    private fun validateRegistrationData(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Pair<Boolean, String> {
        // Check if any field is empty
        if (firstName.isEmpty()) {
            return Pair(false, "First name cannot be empty.")
        }
        if (lastName.isEmpty()) {
            return Pair(false, "Last name cannot be empty.")
        }
        if (email.isEmpty()) {
            return Pair(false, "Email cannot be empty.")
        }
        if (password.isEmpty()) {
            return Pair(false, "Password cannot be empty.")
        }
        if (confirmPassword.isEmpty()) {
            return Pair(false, "Please confirm your password.")
        }

        // Check if password and confirm password match
        if (password != confirmPassword) {
            return Pair(false, "Passwords do not match.")
        }

        // If all checks passed
        return Pair(true, "Registration data is valid.")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}