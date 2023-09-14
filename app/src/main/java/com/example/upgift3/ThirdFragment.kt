package com.example.upgift3

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class ThirdFragment : Fragment() {

    private val apiKey = "fd2051c6d164c11acba440dd8027fab0"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val cityInput = view.findViewById<EditText>(R.id.city_input)
        val fetchWeatherButton = view.findViewById<Button>(R.id.fetch_weather_button)
        val weatherInfo = view.findViewById<TextView>(R.id.weather_info)

        fetchWeatherButton.setOnClickListener {
            val city = cityInput.text.toString()
            fetchWeather(city, weatherInfo)
        }

        return view
    }

    private fun fetchWeather(city: String, weatherInfo: TextView) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = weatherService.getWeather(city, apiKey)
                val info = "${response.weather[0].description}, Temp: ${response.main.temp}°C"
                weatherInfo.text = info
            } catch (e: Exception) {
                weatherInfo.text = "Error fetching weather"
            }
        }
    }
}
