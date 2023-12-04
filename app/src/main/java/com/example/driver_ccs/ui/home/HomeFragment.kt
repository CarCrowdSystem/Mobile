package com.example.driver_ccs.ui.home

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.FragmentHomeBinding
import com.example.driver_ccs.extensions.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.TaskExecutors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class HomeFragment : Fragment(), OnMapReadyCallback {

    private val binding: FragmentHomeBinding by viewBinding()
    private val adapter: HomeAdapter by lazy { HomeAdapter() }
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)

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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment = childFragmentManager.findFragmentById(R.id.fc_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.getMapAsync { googleMap ->
            setupMarkers(googleMap)
        }

        viewModel.getParkingLots()
        viewModel.verifyLoggedUser()

        setupRecyclerView()
        observe()
        setListener()
    }

    private fun setupRecyclerView() {
        binding.rvParking.layoutManager =
            LinearLayoutManager(requireContext(), HORIZONTAL, false)
        binding.rvParking.adapter = adapter
    }


    private fun setListener() {
        binding.btnRefreshMap.setOnClickListener {
            getDeviceLocation(mMap)
        }
    }

    private fun observe() {
        viewModel.listParking.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
        viewModel.userLogged.observe(viewLifecycleOwner) { logged ->
            if (!logged) {
                findNavController().navigate(R.id.action_nav_home_to_nav_login)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d("***HomeFragment", "onMapReady called")
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val currentLocation = LatLng(location.latitude, location.longitude)

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f))
                }
            }

        lifecycleScope.launch {
            getDeviceLocation(googleMap)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation(googleMap: GoogleMap) {
        Log.d("***HomeFragment", "Mapa Atualizado")
        try {
            mMap.clear()
            setupMarkers(googleMap)
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnCompleteListener(TaskExecutors.MAIN_THREAD) { task ->
                if (task.isSuccessful) {
                    val lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        mMap.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    lastKnownLocation.latitude,
                                    lastKnownLocation.longitude
                                )
                            ).title("Você está aqui!")
                        )
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    lastKnownLocation.latitude,
                                    lastKnownLocation.longitude
                                ), 16f
                            )
                        )
                    }

                    Log.d("***HomeFragment", "LATITUDE: ${lastKnownLocation.latitude}")
                    Log.d("***HomeFragment", "LONGITUDE: ${lastKnownLocation.longitude}")
                } else {
                    Log.d("***HomeFragment", "Current location is null. Using defaults.")
                    Log.e("***HomeFragment", "Exception: %s", task.exception)
                    mMap.moveCamera(
                        CameraUpdateFactory
                            .newLatLngZoom(defaultLocation, 16f)
                    )
                    mMap.uiSettings.isMyLocationButtonEnabled = true
                }
            }
        } catch (e: SecurityException) {
            Log.e("***HomeFragment", e.message, e)
        } catch (e: NullPointerException) {
            Log.e("***HomeFragment", e.message, e)
        }
    }

    private fun setupMarkers(googleMap: GoogleMap) {
        viewModel.getParkingLots()
        viewModel.parkingLotPosition.observe(viewLifecycleOwner) { parkingLotList ->
            if (parkingLotList != null) {
                Log.d("***setupMarkers", "$parkingLotList")

                val markers = parkingLotList.map { parkingLot ->
                    LatLng(parkingLot.lat.toDouble(), parkingLot.long.toDouble())
                }

                markers.forEachIndexed { index, markerPosition ->
                    val parkingMarkerOptions = MarkerOptions()
                        .position(markerPosition)
                        .title(parkingLotList[index].place_id.toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

                    googleMap.addMarker(parkingMarkerOptions)
                }

//                if (markers.isNotEmpty()) {
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markers[0], 16.0f))
//                }
            }
        }
    }
}