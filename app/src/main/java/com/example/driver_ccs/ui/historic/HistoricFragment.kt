package com.example.driver_ccs.ui.historic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driver_ccs.databinding.FragmentHistoricBinding
import com.example.driver_ccs.extensions.toggle
import com.example.driver_ccs.extensions.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class HistoricFragment : Fragment() {

    private val binding: FragmentHistoricBinding by viewBinding()
    private val viewModel: HistoricViewModel by viewModels()
    private val adapter: HistoricAdapter by lazy { HistoricAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getHistoric()
        setupRecyclerView()
        observe()
        setListener()
    }

    private fun setupRecyclerView() {
        binding.rvHistoric.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistoric.adapter = adapter
    }

    private fun setListener() {
        binding.swipeHistoric.setOnRefreshListener {
            viewModel.getHistoric()
            binding.swipeHistoric.isRefreshing = false
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.historicList.observe(viewLifecycleOwner) {
                adapter.updateHistoric(it)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if(isLoading) {
                binding.apply {
                    rvHistoric.toggle(!isLoading)
                    pbLoading.toggle(isLoading)
                }
            } else {
                binding.apply {
                    rvHistoric.toggle(!isLoading)
                    pbLoading.toggle(isLoading)
                }
            }
        }
        viewModel.alert.observe(viewLifecycleOwner) { alert ->
            if(alert.showStatus()) {
                Snackbar.make(
                    binding.root,
                    "Sucesso!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(
                    binding.root,
                    "${alert.showMessage()}!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}