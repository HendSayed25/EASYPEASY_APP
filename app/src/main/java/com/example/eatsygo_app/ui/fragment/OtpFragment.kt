package com.example.eatsygo_app.ui.fragment

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eatsygo_app.databinding.FragmentOtpBinding

class OtpFragment : Fragment() {

    private lateinit var otpInputs: Array<EditText>
    private lateinit var binding: FragmentOtpBinding
    private lateinit var otp: StringBuilder
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater, container, false)
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

        for (i in otpInputs.indices) {
            otpInputs[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpInputs.size - 1) {
                        otpInputs[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(p0: android.text.Editable?) {}
            })
        }

        otp = StringBuilder()
        for (otpInput in otpInputs) {
            otp.append(otpInput.text.toString())
        }
        Toast.makeText(context, otp, Toast.LENGTH_LONG).show()
    }

}