package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentOtpBinding

class OtpFragment : Fragment() {

    private lateinit var otpInputs: Array<EditText>
    private var _binding: FragmentOtpBinding? = null
    private val binding
        get() = _binding!!

    // private lateinit var otp: StringBuilder
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        otpInputs = arrayOf(
            binding.otpInput1,
            binding.otpInput2,
            binding.otpInput3,
            binding.otpInput4,
            binding.otpInput5,
            binding.otpInput6
        )

        for (curIndex in otpInputs.indices) {
            otpInputs[curIndex].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && curIndex < otpInputs.size - 1) {
                        otpInputs[curIndex + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(p0: android.text.Editable?) {

//                    otp = StringBuilder()
//                    for (otpInput in otpInputs) {
//                        otp.append(otpInput.text.toString())
//                    }

                    if (curIndex == 5) {
                        // Toast.makeText(context, otp, Toast.LENGTH_LONG).show()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_otpFragment_to_successfullyOtpFragment)
                    }

                }
            })
        }

        binding.backPage.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_otpFragment_to_confirmPhoneNumberFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}