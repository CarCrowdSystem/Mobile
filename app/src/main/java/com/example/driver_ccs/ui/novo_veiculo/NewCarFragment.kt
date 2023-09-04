package com.example.driver_ccs.ui.novo_veiculo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.driver_ccs.databinding.FragmentNewVehicleBinding

class NewCarFragment : Fragment() {

    private lateinit var binding: FragmentNewVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewVehicleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}