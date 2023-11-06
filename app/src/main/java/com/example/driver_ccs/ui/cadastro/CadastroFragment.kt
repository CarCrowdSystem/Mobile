package com.example.driver_ccs.ui.cadastro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentCadastroBinding
import com.example.driver_ccs.extensions.viewBinding
import com.example.driver_ccs.ui.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class CadastroFragment : Fragment() {

    private val binding: FragmentCadastroBinding by viewBinding()
    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CadastroViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        observe()
    }

    private fun setListener() {
        binding.btDoCadastro.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.register(name, email, password)
            } else {
                Snackbar.make(
                    binding.root,
                    "Todos os campos devem estar preenchidos!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun observe() {
        viewModel.user.observe(viewLifecycleOwner) { userStatus ->
            if (userStatus.showStatus()) {
                findNavController().navigate(R.id.action_nav_cadastro_to_nav_success)
            } else {
                findNavController().navigate(R.id.action_nav_cadastro_to_nav_error_register)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.pbLoading.visibility = View.VISIBLE
                binding.ntScroll.visibility = View.GONE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }
    }
}