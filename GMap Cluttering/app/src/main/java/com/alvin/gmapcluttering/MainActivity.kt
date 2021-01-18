package com.alvin.gmapcluttering

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var gMap: GoogleMap

    private lateinit var clusterManager: ClusterManager<MyItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)


    }

    override fun onMapReady(gMaps: GoogleMap?) {
        gMaps?.let {
            gMap = it
        }
        gMap.uiSettings.isMyLocationButtonEnabled = true
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        gMap.isMyLocationEnabled = true

        setUpCluster()

    }


    @SuppressLint("PotentialBehaviorOverride")
    private fun setUpCluster() {
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(11.127123, 78.656891), 10f))

        clusterManager = ClusterManager(this, gMap)
        clusterManager.setAnimation(true)
        clusterManager.renderer.setAnimation(true)

        //Custom made
        clusterManager.renderer = CustomClusterRenderer(this@MainActivity, gMap, clusterManager)

        gMap.setOnCameraIdleListener(clusterManager)
        gMap.setOnMarkerClickListener(clusterManager)

        addItems()
    }

    private fun addItems() {

        val listOfLatLng = listOf(
            LatLng(12.120000, 76.680000),
            LatLng(24.879999, 74.629997),
            LatLng(16.994444, 73.300003),
            LatLng(24.794500, 73.055000),
            LatLng(24.794500, 73.055000),
            LatLng(21.250000, 81.629997),
            LatLng(16.166700, 74.833298),
            LatLng(26.850000, 80.949997),
            LatLng(28.610001, 77.230003),
            LatLng(19.076090, 72.877426),
            LatLng(14.167040, 75.040298),
            LatLng(26.540457, 88.719391),
            LatLng(24.633568, 87.849251),
            LatLng(28.440554, 74.493011),
            LatLng(24.882618, 72.858894),
            LatLng(16.779877, 74.556374),
            LatLng(12.715035, 77.281296),
            LatLng(17.143908, 79.623924),
            LatLng(13.340881, 74.742142),
            LatLng(15.478569, 78.483093)
        )


        for (i in listOfLatLng.indices) {
            var count = 0
            val offsetItem =
                MyItem(listOfLatLng[i], "Title $count", "Dec $count")
            clusterManager.addItem(offsetItem)
            count += 1
        }
    }


}


