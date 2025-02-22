package ru.ivanalesh.simpletoolkit

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("sys") val sys: Sys, // Добавляем данные о восходе и закате
    @SerializedName("name") val cityName: String
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double, // Ощущаемая температура
    @SerializedName("temp_min") val tempMin: Double, // Минимальная температура
    @SerializedName("temp_max") val tempMax: Double, // Максимальная температура
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Sys(
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)