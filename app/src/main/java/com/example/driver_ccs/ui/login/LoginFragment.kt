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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val window: Window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.component)
        observe()
        setListener()
    }

    private fun setListener(){
        binding.btDoLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.doLogin(email, password)
        }

        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_nav_login_to_nav_cadastro)
        }
    }

    private fun observe() {
        viewModel.login.observe(viewLifecycleOwner) { userStatus ->
            if(userStatus.showStatus()) {
//                goNextScreen()
                findNavController().navigate(R.id.action_nav_login_to_nav_home)
            } else {
                findNavController().navigate(R.id.action_nav_login_to_nav_error)
//                val snackbar = Snackbar.make(
//                    binding.root,
//                    "Erro no login",
//                    Snackbar.LENGTH_LONG
//                )
//
//                snackbar.setAction("Tentar novamente") {  }
//
//                snackbar.show()
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

    private fun goNextScreen() {
        findNavController().navigate(R.id.action_nav_login_to_nav_home)
    }

    private fun displayView(isVisible: Boolean){
        binding.ivLogo.toggle(isVisible)
        binding.etEmail.toggle(isVisible)
        binding.etPassword.toggle(isVisible)
        binding.tvForgetPassword.toggle(isVisible)
        binding.btDoLogin.toggle(isVisible)
        binding.btRegister.toggle(isVisible)
    }
}