package com.example.eatsygo_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentProfileBinding
import com.example.eatsygo_app.utils.AuthViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding
        get() = _binding!!

    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.username.observe(viewLifecycleOwner) { username ->
            binding.userName.text = username
        }
        authViewModel.phone.observe(viewLifecycleOwner) { phone ->
            binding.userPhone.text = phone
        }
        authViewModel.email.observe(viewLifecycleOwner) { email ->
            binding.userGmail.text = email
        }
        authViewModel.username.observe(viewLifecycleOwner) { username ->
            binding.userName.text = username
        }
        authViewModel.walletValue.observe(viewLifecycleOwner) { walletValue ->
            binding.bounsMoney.text = "$$walletValue"
        }
        authViewModel.orderCount.observe(viewLifecycleOwner) { orderCount ->
            binding.orderTxt.text = "$orderCount"
        }

        binding.btnLogOut.setOnClickListener {
            authViewModel.setLoginState(false)
            authViewModel.clearSensitiveData()
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_auth_nav)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}