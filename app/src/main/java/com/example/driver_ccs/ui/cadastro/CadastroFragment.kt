package com.example.driver_ccs.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.driver_ccs.databinding.FragmentCadastroBinding

class CadastroFragment: Fragment() {

    private lateinit var binding: FragmentCadastroBinding
    private val viewModel: CadastroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        binding = FragmentCadastroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}