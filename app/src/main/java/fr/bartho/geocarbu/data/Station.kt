package fr.bartho.geocarbu.data

import com.google.gson.annotations.SerializedName

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

data class Station(

    @SerializedName("datasetid") var datasetid: String? = null,
    @SerializedName("recordid") var recordid: String? = null,
    @SerializedName("fields") var fields: Fields? = Fields(),
    @SerializedName("geometry") var geometry: Geometry? = Geometry(),
    @SerializedName("record_timestamp") var recordTimestamp: String? = null
)