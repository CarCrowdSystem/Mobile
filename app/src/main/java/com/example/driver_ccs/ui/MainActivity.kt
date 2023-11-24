package com.example.driver_ccs.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.NavigationUI
import com.example.driver_ccs.R
import com.example.driver_ccs.databinding.ActivityMainBinding
import com.example.driver_ccs.ui.home.HomeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    // -23.5868031,-46.6847268
    // Av. Brig. Faria Lima, 3477 - 18º Andar - Itaim Bibi, São Paulo - SP, 04538-133

    private val places = arrayListOf(
        Place(
            "Google",
            LatLng(-23.5868031, -46.6843406),
            "Av. Brig. Faria Lima, 3477 - 18º Andar - Itaim Bibi, São Paulo - SP, 04538-133"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fc_map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }
        setSupportActionBar(binding.appBarMain.toolbar)
//        obterLocalizacao()
        setupNavigation()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.component))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavigation() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_historic,
                R.id.nav_new_car,
                R.id.nav_user_profile
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            if(menuItem.itemId == R.id.nav_logout) {
                mainViewModel.logout()
                findNavController(R.id.nav_host_fragment_content_main).popBackStack()

//                Testar depois
//                NavigationUI.onNavDestinationSelected(menuItem, navController)
//                drawerLayout.closeDrawer(GravityCompat.START)

                finish()
            } else {
                NavigationUI.onNavDestinationSelected(menuItem, navController)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach {
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(it.name)
                    .snippet(it.address)
                    .position(it.latLng)
            )
        }
    }

//    private fun obterLocalizacao() {
//        val fusedLocationClient: FusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(this)
//
//        // Configurar a solicitação de localização
//        val locationRequest = LocationRequest()
//        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest.interval = 10000 // 10 segundos
//
//        // Obter a localização atual
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        fusedLocationClient.requestLocationUpdates(locationRequest, null)
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener(this, OnSuccessListener { location ->
//                if (location != null) {
//                    val latitude = location.latitude
//                    val longitude = location.longitude
//                    // Faça algo com a latitude e a longitude
//                }
//            })
//    }
}

data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String
)