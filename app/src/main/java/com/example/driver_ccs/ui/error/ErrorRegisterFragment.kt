package com.example.driver_ccs.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentErrorBinding
import com.example.driver_ccs.databinding.FragmentRegisterErrorBinding
import com.example.driver_ccs.extensions.viewBinding

class ErrorRegisterFragment: Fragment() {

    private val binding: FragmentRegisterErrorBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val window: Window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.component)
        setListener()
    }

    private fun setListener() {
        binding.btWithdrawTryAgain.setOnClickListener {
            findNavController().navigate(R.id.action_nav_error_register_to_nav_cadastro)
        }
    }
}