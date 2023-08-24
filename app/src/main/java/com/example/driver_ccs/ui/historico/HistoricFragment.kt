package com.example.driver_ccs.ui.historico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driver_ccs.databinding.FragmentHistoricBinding
import kotlinx.coroutines.launch

class HistoricFragment : Fragment() {

    private lateinit var binding: FragmentHistoricBinding
    private val viewModel: HistoricViewModel by viewModels()
    private val adapter: HistoricAdapter by lazy { HistoricAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoricBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getHistoric()
        setupRecyclerView()
        requestData()
    }

    private fun setupRecyclerView() {
        binding.rvHistoric.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistoric.adapter = adapter
    }

    private fun requestData() {
        lifecycleScope.launch {
            viewModel.historicList.observe(viewLifecycleOwner){
                adapter.updateHistoric(it)
            }
        }
    }

}