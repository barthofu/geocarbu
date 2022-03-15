package fr.bartho.geocarbu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.bartho.geocarbu.databinding.ActivityMainBinding
import fr.bartho.geocarbu.async.GetJSONData


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }


    fun ladData(){

    GetJSONData()

    }


}