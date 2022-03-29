package fr.bartho.geocarbu.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient

data class LocationData(var latitude: Double, var longitude: Double)

object Location {

    @SuppressLint("StaticFieldLeak")
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var currentLocation: LocationData? = LocationData(45.764043, 4.835659)

    @SuppressLint("VisibleForTests")
    fun init(context: Context) {
        this.fusedLocationClient = FusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun updateCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            val tempo = location
            if (location != null) {
                Log.d("Location", "${location.latitude} ${location.longitude}")
                currentLocation = LocationData(location.latitude, location.longitude)
            }
        }
    }
}