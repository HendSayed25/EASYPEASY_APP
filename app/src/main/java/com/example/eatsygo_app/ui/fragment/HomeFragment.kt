package com.example.eatsygo_app.ui.fragment

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.catOne.setOnClickListener {
            binding.catOneTxt.visibility = View.VISIBLE
            binding.catOneTxt.text = "Man's Clothes"
            binding.catOne.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.gold))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}