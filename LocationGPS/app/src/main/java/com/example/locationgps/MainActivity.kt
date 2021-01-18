package com.example.locationgps

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startLocationUpdates()
        btn=findViewById(R.id.button)
    }

    private fun startLocationUpdates() {
        var locationResult: LocationRequest = LocationRequest.create()
        locationResult.setInterval(10000)
        locationResult.setFastestInterval(5000)
        locationResult.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        var locationSettingsRequest: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationResult)

        var settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        var task: Task<LocationSettingsResponse>
        task = settingsClient.checkLocationSettings(locationSettingsRequest.build())

        task.addOnSuccessListener(this, OnSuccessListener { tResult ->
            btn.text="qswdfghj"

        })
        task.addOnFailureListener(this, OnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                var resolvableApiException: ResolvableApiException = exception
                resolvableApiException.startResolutionForResult(this, 1)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }


}

/* private fun getLocation() {
     mLocationRequest = LocationRequest()
     mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
     mLocationRequest.setInterval(10 * 1000)
     mLocationRequest.setFastestInterval(2000)

     var builder = LocationSettingsRequest.Builder()
     builder.addLocationRequest(mLocationRequest)
     val locationSettingsRequest = builder.build()

     var settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
     settingsClient.checkLocationSettings(locationSettingsRequest)

     registerLocationListner()

 }

 private fun checkPermission(): Boolean {
     if (ContextCompat.checkSelfPermission(
             this,
             Manifest.permission.ACCESS_FINE_LOCATION
         ) == PackageManager.PERMISSION_GRANTED
     ) {
         return true;
     } else {
         requestPermissions()
         return false
     }
 }

 private fun requestPermissions() {
     ActivityCompat.requestPermissions(this, arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"), 1)
 }

 override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
     super.onRequestPermissionsResult(requestCode, permissions, grantResults)
     if (requestCode == 1) {
         if (permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION) {
             registerLocationListner()
         }
     }
 }

 private fun registerLocationListner() {
     // initialize location callback object
     val locationCallback = object : LocationCallback() {
         override fun onLocationResult(locationResult: LocationResult?) {
             onLocationChanged(locationResult!!.getLastLocation())
         }
     }
     // add permission if android version is greater then 23
     if (Build.VERSION.SDK_INT >= 23 && checkPermission()) {
         LocationServices.getFusedLocationProviderClient(this)
             .requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper())
     }
 }

 private fun onLocationChanged(location: Location) {
     // create message for toast with updated latitude and longitude
     var msg = "Updated Location: " + location.latitude + " , " + location.longitude

     // show toast message with updated location
     Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

 }*/

