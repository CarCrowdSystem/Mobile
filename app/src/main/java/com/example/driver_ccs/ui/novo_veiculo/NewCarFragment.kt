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
import com.example.driver_ccs.data.SecurityPreferences
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
//        binding.btnGetCarData.setOnClickListener {
//            if (binding.etPlate.text.toString().length == 7) {
//                val placa = binding.etPlate.text.toString()
//                viewModel.getCarData(placa)
//            } else {
//                Snackbar.make(
//                    binding.root,
//                    "A placa deve ter 7 caracteres",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
//        }
        binding.btnAddCar.setOnClickListener {
//            if (binding.etPlate.text.toString().length == 7 &&
//                binding.etModel.text.isNotEmpty() &&
//                binding.etMarca.text.isNotEmpty()) {

//            val placa = binding.etPlate.text.toString()
//            val modelo = binding.etModel.text.toString()
//            val marca = binding.etMarca.text.toString()

            val placa = "DDDD000"
            val modelo = "Skyline"
            val marca = "Nissan"

            viewModel.registerCar(placa, modelo, marca)
//            } else {
//                Snackbar.make(
//                    binding.root,
//                    "Os campos devem estar preechidos!",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
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
        viewModel.alert.observe(viewLifecycleOwner) {
            if(it.showStatus()) {
                Snackbar.make(
                    binding.root,
                    "Carro adicionado com sucesso!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(
                    binding.root,
                    "Erro ao adicionar carro!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvHistoric.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistoric.adapter = adapter
    }
}