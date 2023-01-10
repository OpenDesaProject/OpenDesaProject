package com.example.opendesa.util

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.StrictMode
import android.text.Html
import android.text.Spanned
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

class Helper {
    companion object {
        const val URL = "http://duaempat.my.id/profile-kecamatan/public/api"
        fun fromHTML(html:String) : Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(html)
            }
        }

        fun getRespFromAPI(url:String) : String {
            try {
                val sb = StringBuilder()
                val reader = BufferedReader(
                    InputStreamReader(URL(URL + url).openConnection().getInputStream(), "UTF-8")
                )
                var inputLine: String?
                while (reader.readLine().also { inputLine = it } != null) sb.append(inputLine)
                reader.close()
                return sb.toString()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return ""
        }


        fun getImageFromURL(url:String) : Drawable? {
            try {
                if (Build.VERSION.SDK_INT > 9) {
                    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
                }
                return Drawable.createFromStream(URL(url).content as InputStream?, "src name")
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return null
        }


    }
}