package fr.bartho.geocarbu.utils

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import fr.bartho.geocarbu.async.GetJSONData
import java.net.URL

class APIProxy(
    val url: String = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=prix_des_carburants_j_7",
    val lang: String = "FR",
    var q: String = "",
    val rows: Int = 10,
    var start: Int = 0,
    val facet: String = "facet=cp&facet=pop&facet=city&facet=automate_24_24&facet=fuel&facet=shortage&facet=update&facet=services&facet=brand"
) {

    var response: ResponseItem? = null

    fun generateUrl(): String {

        return "$url&lang=$lang&rows=$rows&start=$start&$facet";
    }


    init {
        var data = GetJSONData().doInBackground(URL(generateUrl()))
        val gson = Gson()
        response = gson.fromJson(data, ResponseItem::class.java)
    }

    public fun next() {
        start += rows
        val gson = Gson()
        val data = GetJSONData().doInBackground(URL(generateUrl()))

        for (i in gson.fromJson(data, ResponseItem::class.java).records) {
            response?.records?.add(i)
        }
    }

    public fun query(query: String) {
        q = query;

    }

    data class ResponseItem(
        @SerializedName("nhits")
        val nhits: Int,
        val datasetid: String,
        val recordid: String,
        @SerializedName("records") var records: ArrayList<Records> = arrayListOf(),
        val geometry: Geometry,

        val recordTimestamp: String
    )

    data class Fields(

        @SerializedName("geo_point") var geoPoint: ArrayList<Double> = arrayListOf(),
        @SerializedName("city") var city: String? = null,
        @SerializedName("automate_24_24") var automate2424: String? = null,
        @SerializedName("timetable") var timetable: String? = null,
        @SerializedName("services") var services: String? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("brand") var brand: String? = null,
        @SerializedName("pop") var pop: String? = null,
        @SerializedName("shortage") var shortage: String? = null,
        @SerializedName("cp") var cp: String? = null,
        @SerializedName("id") var id: String? = null,
        @SerializedName("fuel") var fuel: String? = null,
        @SerializedName("address") var address: String? = null,
        @SerializedName("update") var update: String? = null,
        @SerializedName("price_gazole") var priceGazole: Double? = null
    )

    data class Geometry(
        @SerializedName("type") var type: String? = null,
        @SerializedName("coordinates") var coordinates: ArrayList<Double> = arrayListOf()
    )

    data class Records(

        @SerializedName("datasetid") var datasetid: String? = null,
        @SerializedName("recordid") var recordid: String? = null,
        @SerializedName("fields") var fields: Fields? = Fields(),
        @SerializedName("geometry") var geometry: Geometry? = Geometry(),
        @SerializedName("record_timestamp") var recordTimestamp: String? = null

    )
}