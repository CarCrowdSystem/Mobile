package com.example.driver_ccs.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.driver_ccs.databinding.FragmentCadastroBinding
import com.example.driver_ccs.extensions.viewBinding

class CadastroFragment: Fragment() {

    private val binding: FragmentCadastroBinding by viewBinding()
    private val viewModel: CadastroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        return binding.root
    }
}