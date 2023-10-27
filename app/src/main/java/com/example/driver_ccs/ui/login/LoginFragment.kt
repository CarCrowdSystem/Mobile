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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentLoginBinding
import com.example.driver_ccs.extensions.viewBinding

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
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_nav_login_to_nav_cadastro)
        }
    }

    private fun observe() {
        viewModel.login.observe(viewLifecycleOwner) { userStatus ->
            if(userStatus.showStatus()) {
                Log.d("***Teste login", "logged")
                goNextScreen()
            }

            Toast.makeText(context, userStatus.showMessage(), Toast.LENGTH_LONG).show()
        }
    }

    private fun goNextScreen() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_nav_login_to_nav_home)
    }
}