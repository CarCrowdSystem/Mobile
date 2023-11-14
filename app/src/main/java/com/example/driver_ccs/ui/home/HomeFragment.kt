package com.example.driver_ccs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentHomeBinding
import com.example.driver_ccs.extensions.viewBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by viewBinding()
    private val adapter: HomeAdapter by lazy { HomeAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val window: Window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.component)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getParkingLots()
        viewModel.verifyLoggedUser()
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        binding.rvEstacionamento.layoutManager =
            LinearLayoutManager(requireContext(), HORIZONTAL, false)
        binding.rvEstacionamento.adapter = adapter
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.listParking.observe(viewLifecycleOwner) {
                adapter.update(it)
            }

//            viewModel.userLogged.observe(viewLifecycleOwner) { logged ->
//                if(!logged) {
//                    findNavController().navigate(R.id.action_nav_home_to_nav_login)
//                }
//            }
        }
    }
}
