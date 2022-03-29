package fr.bartho.geocarbu.fragment

import android.content.Intent
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fr.bartho.geocarbu.R
import fr.bartho.geocarbu.activity.DetailsActivity
import fr.bartho.geocarbu.utils.Location
import fr.bartho.geocarbu.utils.StationsManager

data class LocationData(var latitude: Double, var longitude: Double)

class StationsMap : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val location = LatLng(Location.currentLocation!!.latitude, Location.currentLocation!!.longitude)
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Votre position"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 11.0f))

        for (station in StationsManager.stations) {
            if (station.fields?.geoPoint?.size == 2) {
                val location = LatLng(station.fields!!.geoPoint[0], station.fields!!.geoPoint[1])
                googleMap.addMarker(MarkerOptions().position(location).title(station.fields!!.name))
            }
        }

        googleMap.setOnMarkerClickListener { marker ->
            val station = StationsManager.stations.find { it.fields?.name == marker.title }
            if (station != null) {
                val intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra("station", station)
                startActivity(intent)
            }
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stations_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}