package com.example.driver_ccs.ui.novo_veiculo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentNewVehicleBinding
import com.example.driver_ccs.extensions.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class NewCarFragment : Fragment() {

    private val binding: FragmentNewVehicleBinding by viewBinding()
    private lateinit var viewModel: NewCarViewModel
    private val adapter: NewCarAdapter by lazy { NewCarAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewCarViewModel::class.java]
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
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
        viewModel.getCarsList()
        observe()
        setListener()
        setupRecyclerView()
    }

    private fun setListener() {
        binding.btnGetCarData.setOnClickListener {
            if (binding.etPlate.text.toString().length == 7) {
                viewModel.getCarData(binding.etPlate.text.toString())
            } else {
                Snackbar.make(
                    binding.root,
                    "A placa deve ter 7 caracteres",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun observe() {
        viewModel.carData.observe(viewLifecycleOwner) {
            binding.etMarca.setText(it.marca)
            binding.etModel.setText(it.modelo)
        }
        lifecycleScope.launch {
            viewModel.carsListData.observe(viewLifecycleOwner) {
                adapter.updateCarList(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvHistoric.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistoric.adapter = adapter
    }
}