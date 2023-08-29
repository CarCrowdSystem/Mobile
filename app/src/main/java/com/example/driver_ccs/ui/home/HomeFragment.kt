package com.example.driver_ccs.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentHomeBinding
import com.example.driver_ccs.ui.login.LoginFragment
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val adapter: HomeAdapter by lazy { HomeAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val window: Window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.orange)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getParkingLots()
        viewModel.verifyLoggedUser()
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        binding.rvEstacionamento.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        binding.rvEstacionamento.adapter = adapter
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.listParking.observe(viewLifecycleOwner){
                adapter.update(it)
            }
        }

        viewModel.userLogged.observe(viewLifecycleOwner) { logged ->
            if (!logged) {
//               val action = HomeFragmentDirections.actionNavHomeToNavLogin()
                findNavController().navigate(R.id.action_nav_home_to_nav_login)
            }
        }
    }
}