package com.weather_exp.app.ui.main

import com.weather_exp.app.data.network.model.weather.WeatherData
import com.weather_exp.app.ui.base.MvpPresenter

interface MainPresenterView<V : MainView> : MvpPresenter<V> {
    fun onWeather(request: WeatherData.Request)
}