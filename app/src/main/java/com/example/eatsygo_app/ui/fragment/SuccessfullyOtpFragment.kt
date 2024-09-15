package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentSuccessfullyOtpBinding

class SuccessfullyOtpFragment : Fragment() {

    private var _binding: FragmentSuccessfullyOtpBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessfullyOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goLoginBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_successfullyOtpFragment_to_loginFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}