package com.example.driver_ccs.ui.cadastro

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentCadastroBinding
import com.example.driver_ccs.extensions.hide
import com.example.driver_ccs.extensions.show
import com.example.driver_ccs.extensions.viewBinding
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

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            // Not needed
        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            // Not needed
        }

        override fun afterTextChanged(editable: Editable?) {
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (password != confirmPassword) {
                Snackbar.make(
                    binding.root,
                    "As senhas devem ser iguais!",
                    Snackbar.LENGTH_LONG
                ).show()
                binding.tvWarningPassword.show()
                binding.tvWarningPasswordConfirmation.show()

                binding.etPassword.setBackgroundResource(R.drawable.edit_style_error)
                binding.etConfirmPassword.setBackgroundResource(R.drawable.edit_style_error)
            } else {
                binding.tvWarningPassword.hide()
                binding.tvWarningPasswordConfirmation.hide()

                binding.etPassword.setBackgroundResource(R.drawable.edit_style)
                binding.etConfirmPassword.setBackgroundResource(R.drawable.edit_style)
            }
        }
    }

    private fun setListener() {
        binding.etPassword.addTextChangedListener(textWatcher)
        binding.etConfirmPassword.addTextChangedListener(textWatcher)

        binding.btDoCadastro.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (password == confirmPassword) {
                binding.tvWarningPassword.hide()
                binding.tvWarningPasswordConfirmation.hide()
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
        binding.btGoToLogin.setOnClickListener {
            findNavController().popBackStack()
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