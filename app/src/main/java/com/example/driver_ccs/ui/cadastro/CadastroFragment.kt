package com.example.driver_ccs.ui.cadastro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
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
        observe()
    }

    private fun setListener() {
        binding.btDoCadastro.setOnClickListener {
            handleSave()
        }
    }

    private fun observe(){
        viewModel.user.observe(viewLifecycleOwner) {
            if(it.showStatus()) {
                findNavController().navigate(R.id.action_nav_cadastro_to_nav_success)
            } else {
                findNavController().navigate(R.id.action_nav_cadastro_to_nav_error)
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

    private fun handleSave() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.register(name, email, password)
    }
}