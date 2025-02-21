package ru.ivanalesh.simpletoolkit

import com.google.gson.annotations.SerializedName

data class WeatherResponce {
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("name") val cityName: String
}
data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)
data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)