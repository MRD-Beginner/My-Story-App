package com.bangkit.storyapp.ui.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.storyapp.R
import com.bangkit.storyapp.connection.pref.UserPreferences
import com.bangkit.storyapp.connection.pref.dataStore
import com.bangkit.storyapp.databinding.ActivityMapsBinding
import com.bangkit.storyapp.ui.StoryViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val bound = LatLngBounds.Builder()

    private val viewModel by viewModels<MapsViewModel> {
        StoryViewModelFactory.getInstance(this)
    }

    private val pref by lazy {
        UserPreferences.getInstance(dataStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityMapsBinding.inflate(layoutInflater)
         setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupMaps()


    }

    private fun setupMaps() {
        binding.menuMaps.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.normal_type -> {
                    mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                }

                R.id.satellite_type -> {
                    mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

                }

                R.id.terrain_type -> {
                    mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

                }

                R.id.hybrid_type -> {
                    mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

                }
                else -> {

                }
            }
            true
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

        moveToUnriCampus()

        viewModel.loadStoriesWithLocation()
        viewModel.storiesLocation.observe(this){storyLocation ->
            storyLocation.forEach{story ->
                val latLng = LatLng(story.lat ?: 0.0, story.lon ?: 0.0)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(story.name)
                        .snippet(story.description)
                )
            }
        }
    }

    private fun moveToUnriCampus() {
        val unriCampus = LatLng(0.488390, 101.376704)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(unriCampus, 20f))
    }
}