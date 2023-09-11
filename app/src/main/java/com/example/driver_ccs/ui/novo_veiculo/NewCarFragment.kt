package com.example.driver_ccs.ui.novo_veiculo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.driver_ccs.databinding.FragmentNewVehicleBinding
import com.example.driver_ccs.extensions.viewBinding

class NewCarFragment : Fragment() {

    private val binding: FragmentNewVehicleBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}