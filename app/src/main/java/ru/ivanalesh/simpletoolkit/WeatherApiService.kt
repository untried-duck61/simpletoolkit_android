package ru.ivanalesh.simpletoolkit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ivanalesh.simpletoolkit.WeatherResponse

interface WeatherApiService {
    @GET("weather")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Call<WeatherResponce>
}