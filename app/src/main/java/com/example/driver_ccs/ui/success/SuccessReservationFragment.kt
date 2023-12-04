package com.example.driver_ccs.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentSuccessBinding
import com.example.driver_ccs.extensions.viewBinding

class SuccessReservationFragment: Fragment() {

    private val binding: FragmentSuccessBinding by viewBinding()

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
        binding.tvSuccess.text = resources.getText(R.string.label_success_reservation)
        window.statusBarColor = resources.getColor(R.color.component)
        setListener()
    }

    private fun setListener() {
        binding.btWithdrawSuccess.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}