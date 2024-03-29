package com.example.driver_ccs.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentLoginBinding
import com.example.driver_ccs.extensions.toggle
import com.example.driver_ccs.extensions.viewBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by viewBinding()
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val window: Window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.component)
        observe()
        setListener()
    }

    private fun setListener() {
        binding.btDoLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.doLogin(email, password)
            viewModel.saveUserPassword(password)
        }
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_nav_login_to_nav_cadastro)
        }
    }

    private fun observe() {
        viewModel.login.observe(viewLifecycleOwner) { userStatus ->
            if (userStatus.showStatus()) {
                findNavController().navigate(R.id.action_nav_login_to_nav_home)
                if (viewModel.passwordValue.value == "0000") {
                    Snackbar.make(
                        binding.root,
                        "Por favor, altere sua senha na aba de perfil!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            } else {
                findNavController().navigate(R.id.action_nav_login_to_nav_error)
                displayView(true)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.pbLoading.toggle(true)
                displayView(false)
            } else {
                binding.pbLoading.toggle(false)
            }
        }
    }

    private fun displayView(isVisible: Boolean) {
        binding.apply {
            ivLogo.toggle(isVisible)
            etEmail.toggle(isVisible)
            etPassword.toggle(isVisible)
            btDoLogin.toggle(isVisible)
            btRegister.toggle(isVisible)
        }
    }
}