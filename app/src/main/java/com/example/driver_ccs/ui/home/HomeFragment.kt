package com.example.driver_ccs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit  var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val window: Window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.orange)
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}