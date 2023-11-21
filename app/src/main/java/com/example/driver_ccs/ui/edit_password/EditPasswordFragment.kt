package com.example.driver_ccs.ui.edit_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentEditPasswordBinding
import com.example.driver_ccs.extensions.toggle
import com.example.driver_ccs.extensions.viewBinding

class EditPasswordFragment : Fragment() {

    private val binding: FragmentEditPasswordBinding by viewBinding()
    private lateinit var viewModel: EditPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EditPasswordViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setListener()
        observe()
    }

    private fun setListener() {
        binding.btnEditPassword.setOnClickListener {
            val oldPassword = binding.etPassword.text.toString()
            val newPassword = binding.etConfirmPassword.text.toString()

            viewModel.editPassword(oldPassword, newPassword)

//            viewModel.editPassword(binding.etPassword.text.toString())
            // Teste
//            if(binding.etPassword.text.isNotEmpty() && binding.etConfirmPassword.text.isNotEmpty()) {
//                findNavController().navigate(R.id.action_nav_edit_password_to_nav_success_edit_password)
//            } else {
//                findNavController().navigate(R.id.action_nav_edit_password_to_nav_edit_error)
//            }
        }
    }

    private fun observe() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if(isLoading) {
                binding.pbLoading.toggle(true)
                displayView(false)
            } else {
                binding.pbLoading.toggle(false)
                displayView(true)
            }
        }
        viewModel.status.observe(viewLifecycleOwner) {
            if(it.showStatus()) {
                findNavController().navigate(R.id.action_nav_edit_password_to_nav_success_edit_password)
            } else {
                findNavController().navigate(R.id.action_nav_edit_password_to_nav_edit_error)
            }
        }
    }

    private fun displayView(isVisible: Boolean) {
        binding.apply {
            ivLogo.toggle(isVisible)
            etPassword.toggle(isVisible)
            etConfirmPassword.toggle(isVisible)
            btnEditPassword.toggle(isVisible)
        }
    }
}