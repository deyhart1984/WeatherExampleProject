package com.weather_exp.app.ui.main

import com.weather_exp.app.data.network.model.weather.WeatherData
import com.weather_exp.app.ui.base.MvpView

interface MainView: MvpView {
    fun onSuccess(response: WeatherData.Response)
    fun onFailed(msg: String)
}