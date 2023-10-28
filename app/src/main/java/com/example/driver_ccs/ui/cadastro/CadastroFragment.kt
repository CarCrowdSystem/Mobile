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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.btDoCadastro.setOnClickListener {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val senha = binding.etPassword.text.toString()

        viewModel.cadastro(name, email, senha)
    }
}