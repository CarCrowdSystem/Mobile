package com.example.driver_ccs.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        observe()
    }

    private fun setListener(){
        binding.btDoLogin.setOnClickListener {
            val email = binding.etEmail .text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.doLogin(email, password)
        }
    }

    private fun observe() {
        viewModel.login.observe(viewLifecycleOwner) {
            if(it == true){
                Log.d("***Teste login", "logged")
                goNextScreen()
            }

            Log.d("***Teste login", "error")
        }
    }

    private fun goNextScreen() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_nav_login_to_nav_home)
    }
}