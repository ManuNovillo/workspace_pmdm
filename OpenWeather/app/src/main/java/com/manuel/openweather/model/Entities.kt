package com.manuel.openweather.model

import com.google.gson.annotations.SerializedName

data class OpenWeather(
    @SerializedName("list")
    var dias: List<Dia>
)

data class Dia(
    @SerializedName("main")
    var temp: Temperatura,
    var weather: List<Cielo>,
    var pop: Float,
    @SerializedName("dt_txt")
    var fecha: String
)

data class Cielo(
    var description: String,
    var icon: String
)

data class Temperatura(
    @SerializedName("temp_min")
    var min: Float,
    @SerializedName("temp_max")
    var max: Float
)


