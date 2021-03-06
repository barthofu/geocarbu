package fr.bartho.geocarbu.utils

import android.os.AsyncTask
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class HTTPRequest : AsyncTask<URL?, Any, String>() {

    public override fun doInBackground(vararg urls: URL?): String {

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        try {
            val url: URL? = urls[0]
            val urlConnection = url?.openConnection() as HttpURLConnection
            var text: String = ""

            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
                text = input.readLine()
                input.close()
            }
            urlConnection.disconnect()

            return text
        } catch (e: Exception) {
            // create empty array list
            return ""
        }

    }

    public override fun onPostExecute(result: String) {
        println(result)
    }

}