package com.weather_exp.app.data.network.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherData {
    class Request {
        @SerializedName("q")
        @Expose
        var q : String? = null

        @SerializedName("appid")
        @Expose
        var appid : String? = null
    }

    class Response {
        @SerializedName("cod")
        @Expose
        var cod: String? = null

        @SerializedName("message")
        @Expose
        var message: Int? = null

        @SerializedName("cnt")
        @Expose
        var cnt: Int? = null

        @SerializedName("list")
        @Expose
        var list: MutableList<DataList>? = null

        @SerializedName("city")
        @Expose
        var city: City? = null
    }

    class City {

        @SerializedName("id")
        @Expose
        var id: Int = 0

        @SerializedName("name")
        @Expose
        var name: String? = null
    }

    class DataList {

        @SerializedName("dt")
        @Expose
        var dt: Int = 0

        @SerializedName("main")
        @Expose
        var main: Main? = null

        @SerializedName("weather")
        @Expose
        var weather: MutableList<Weather>? = null

        @SerializedName("clouds")
        @Expose
        var clouds: Clouds? = null

        @SerializedName("wind")
        @Expose
        var wind: Wind? = null

        @SerializedName("dt_txt")
        @Expose
        var dt_txt: String? = null
    }

    class Main {
        @SerializedName("temp")
        @Expose
        var temp: Double? = null

        @SerializedName("feels_like")
        @Expose
        var feels_like: Double? = null

        @SerializedName("temp_min")
        @Expose
        var temp_min: Double? = null

        @SerializedName("temp_max")
        @Expose
        var temp_max: Double? = null
    }

    class Weather {
        @SerializedName("id")
        @Expose
        var id: Int = 0

        @SerializedName("main")
        @Expose
        var main: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("icon")
        @Expose
        var icon: String? = null
    }

    class Clouds {
        @SerializedName("all")
        @Expose
        var all: Int = 0
    }

    class Wind {
        @SerializedName("speed")
        @Expose
        var speed: Double? = null

        @SerializedName("deg")
        @Expose
        var deg: Int? = 0
    }
}
