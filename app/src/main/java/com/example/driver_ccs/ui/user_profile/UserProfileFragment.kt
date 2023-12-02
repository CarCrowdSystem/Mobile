package com.example.driver_ccs.ui.user_profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.driver_ccs.R
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
                binding.tvUserName.text = binding.etUserName.text
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
        viewModel.userPicture.let {
            if (it.isNotEmpty()) {
                Log.d("***userPicture", it)
                Glide.with(this)
                    .load(it.toUri())
                    .into(binding.ivPhoto)
            }
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
        binding.btnEditPassword.setOnClickListener {
            findNavController().navigate(R.id.action_nav_user_profile_to_nav_edit_password)
        }
        binding.btnAddPhoto.setOnClickListener {
            openGallery()
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

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(galleryIntent)
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val selectedImageUri = result.data?.data
            Glide.with(this)
                .load(selectedImageUri)
                .into(binding.ivPhoto)

            if (selectedImageUri != null) {
                viewModel.saveImageUri(selectedImageUri)
                Snackbar.make(
                    binding.root,
                    "Foto atualizada!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
}