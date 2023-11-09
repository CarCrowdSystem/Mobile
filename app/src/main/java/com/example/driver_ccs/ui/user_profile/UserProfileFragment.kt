package com.example.driver_ccs.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.driver_ccs.databinding.FragmentUserProfileBinding
import com.example.driver_ccs.extensions.toggle
import com.example.driver_ccs.extensions.viewBinding
import com.google.android.material.snackbar.Snackbar

class UserProfileFragment : Fragment() {

    private val binding: FragmentUserProfileBinding by viewBinding()
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UserProfileViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
//        viewModel.getDefaultPicture("Gabriel Romao")
        viewModel.getUserName()
        setListener()
        observe()
    }

    private fun observe() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.pbLoading.toggle(true)
                displayView(false)
            } else {
                binding.pbLoading.toggle(false)
                displayView(true)
            }
        }
        viewModel.alert.observe(viewLifecycleOwner) { alert ->
            if (alert.showStatus()) {
                Snackbar.make(
                    binding.root,
                    "Dados atualizados com sucesso!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(
                    binding.root,
                    "Erro ao atualizar o perfil!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        viewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.tvUserName.text = name.ifEmpty { "Nome user" }
        }
    }

    private fun setListener() {
        binding.btnUserSaveInfo.setOnClickListener {
            val nome = binding.etUserName.text.toString()
            val email = binding.etUserEmail.text.toString()
            val telefone = binding.etUserTelefone.text.toString()
            val cpf = binding.etUserCpf.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && telefone.isNotEmpty() && cpf.isNotEmpty()) {
                viewModel.updateProfile(nome, email, telefone, cpf)
            } else {
                Snackbar.make(
                    binding.root,
                    "Todos os campos devem estar preenchidos!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun displayView(isVisible: Boolean) {
        binding.apply {
            etUserName.toggle(isVisible)
            etUserEmail.toggle(isVisible)
            etUserTelefone.toggle(isVisible)
            etUserCpf.toggle(isVisible)
            btnEditPassword.toggle(isVisible)
            btnUserSaveInfo.toggle(isVisible)
        }
    }
}