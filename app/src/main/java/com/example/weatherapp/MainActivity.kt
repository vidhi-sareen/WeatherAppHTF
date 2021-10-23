package com.example.weatherapp

import android.app.DownloadManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lat=intent.getStringExtra("lat")
        val long=intent.getStringExtra("long")
        getJsonData(lat,long)


    }


    private fun getJsonData(lat:String?,long:String){


        val API_Key="22d8a30f9b48bee05190a70949487881"
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${API_Key}"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                setValues(response)
            },
            Response.ErrorListener { Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show() })


        queue.add(jsonRequest)
    }

    private fun setValues(response:JSONObject){
        city.text=response.getString("name")
        var lat =response.getJSONObject("coord").getString("lat")
        var long =response.getJSONObject("coord").getString("lon")
        coordinates.text="$(lat) , $(long)"
        weather.text=response.getJSONArray("weather".getJsonObject(0).getString())
        var tempr=response.getJSONObject("main".getString("temp"))

    }
}