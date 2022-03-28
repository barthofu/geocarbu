package fr.bartho.geocarbu.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import fr.bartho.geocarbu.data.Geometry
import fr.bartho.geocarbu.data.Station
import java.io.*
import java.net.URL

object StationsManager {

    private const val url: String = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=prix_des_carburants_j_7"
    private const val lang: String = "FR"
    var q: String = ""
    private const val rows: Int = 10
    private var start: Int = 0
    const val facet: String = "facet=cp&facet=pop&facet=city&facet=automate_24_24&facet=fuel&facet=shortage&facet=update&facet=services&facet=brand"

    val SAVE_FILENAME = "saveStations"
    var stations: ArrayList<Station> = ArrayList()

    private fun generateUrl(): String {

        return "$url&lang=$lang&rows=$rows&start=$start&$facet"
    }

    fun loadFromAPI() {

        val data = HTTPRequest().doInBackground(URL(generateUrl()))
        val gson = Gson()
        val obj = gson.fromJson(data, ResponseItem::class.java)

        for (record in obj.records) {
            val station = Station(record.datasetid, record.recordid, record.fields, record.geometry, record.recordTimestamp)
            stations.add(station)
        }
    }

    fun loadFromDisk (context: Context) {

        val directory: File = context.filesDir
        val file = File(directory, SAVE_FILENAME)

        if (file.exists()) {

            try {
                val fileInputStream: FileInputStream = context.applicationContext.openFileInput(SAVE_FILENAME)
                val inputStream = ObjectInputStream(fileInputStream)

                val stations = inputStream.readObject() as ArrayList<Station>
                this.stations = stations

                inputStream.close()
                fileInputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun saveToDisk (context: Context) {

        try {
            val fos: FileOutputStream = context.applicationContext.openFileOutput(SAVE_FILENAME, Context.MODE_PRIVATE)
            val out = ObjectOutputStream(fos)

            out.writeObject(this.stations)

            out.close()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun next() {
        start += rows
        val gson = Gson()
        val data = HTTPRequest().doInBackground(URL(generateUrl()))
        val obj = gson.fromJson(data, ResponseItem::class.java)

        for (record in obj.records) {
            val station = Station(
                record.datasetid,
                record.recordid,
                record.fields,
                record.geometry,
                record.recordTimestamp
            )
            this.stations.add(station)
        }
    }

    fun query(query: String) {
        q = query
    }

    data class ResponseItem(
        @SerializedName("nhits")
        val nhits: Int,
        val datasetid: String,
        val recordid: String,
        @SerializedName("records") var records: ArrayList<Station> = arrayListOf(),
        val geometry: Geometry,

        val recordTimestamp: String
    )


}