package fr.bartho.geocarbu.async

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetJSONData : AsyncTask<URL?, Any, String>() {

    override fun doInBackground(vararg urls: URL?): String {

        val url: URL? = urls[0]
        val urlConnection = url?.openConnection() as HttpURLConnection
        lateinit var text: String

        if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
            text = input.readLine()
            input.close()
        }
        urlConnection.disconnect();

        return text
    }

    override fun onPostExecute(result: String) {
        println(result)
    }

}