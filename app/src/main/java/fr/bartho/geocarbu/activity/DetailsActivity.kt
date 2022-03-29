package fr.bartho.geocarbu.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.bartho.geocarbu.data.Station
import fr.bartho.geocarbu.databinding.ActivityDetailsBinding
import java.time.format.DateTimeFormatter

class DetailsActivity : AppCompatActivity() {

    private val defaultString = "--"
    private lateinit var binding : ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)

        val station: Station = intent.extras?.get("station") as Station

        binding.nameView.text = station.fields?.name
        binding.adressView.text = station.fields?.address + ", " + station.fields?.cp + " " + station.fields?.city

        binding.pricesView.text =
                "Diesel :      ${station.fields?.price_gazole ?: this.defaultString}€\n" +
                "SP95 :         ${station.fields?.price_sp95 ?: this.defaultString}€\n" +
                "SP98 :         ${station.fields?.price_sp98 ?: this.defaultString}€\n" +
                "E10 :            ${station.fields?.price_e10 ?: this.defaultString}€\n"

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val dateTimeFormatted = station.fields?.update?.format(formatter)

        binding.lastUpdate.text = "Dernière mise à jour : ${dateTimeFormatted ?: this.defaultString}"

        setContentView(binding.root)
    }
}