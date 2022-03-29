package fr.bartho.geocarbu.activity

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import fr.bartho.geocarbu.activity.ui.main.SectionsPagerAdapter
import fr.bartho.geocarbu.databinding.ActivityHomeBinding
import fr.bartho.geocarbu.utils.Location
import fr.bartho.geocarbu.utils.StationsManager

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (this.isNetworkAvailable()) {
            Location.init(this)
            Location.updateCurrentLocation()
            StationsManager.setCurrentLocation(Location.currentLocation)
            StationsManager.loadFromAPI()
            StationsManager.saveToDisk(this)
        } else {

            Toast.makeText(this, "Aucune connexion internet disponible", Toast.LENGTH_LONG).show()
            Location.init(this)
            StationsManager.loadFromDisk(this)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onDestroy() {
        super.onDestroy()
        StationsManager.saveToDisk(this)
    }

}