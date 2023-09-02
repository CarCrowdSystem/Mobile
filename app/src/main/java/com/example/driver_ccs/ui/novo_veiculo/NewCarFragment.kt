package com.example.driver_ccs.ui.novo_veiculo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.driver_ccs.databinding.FragmentNovoVeiculoBinding

class NewCarFragment : Fragment() {
    private lateinit var binding: FragmentNovoVeiculoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNovoVeiculoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}