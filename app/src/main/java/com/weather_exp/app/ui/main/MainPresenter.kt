package com.weather_exp.app.ui.main

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.weather_exp.app.data.network.ApiEndPoint
import com.weather_exp.app.data.network.model.weather.WeatherData
import com.weather_exp.app.ui.base.BasePresenter
import org.json.JSONObject

class MainPresenter<V: MainView>: BasePresenter<V>(), MainPresenterView<V> {
    override fun onWeather(request: WeatherData.Request) {
        getMvpView().showLoading()

        AndroidNetworking.post(ApiEndPoint.ENDPOINT_OPENWEATHER)
            .addQueryParameter("mode", "json")
            //.addQueryParameter("cnt", "5")
            .addQueryParameter("units", "metric")
            .addQueryParameter("lang", "id")
            .addQueryParameter("q", request.q)
            .addQueryParameter("appid", request.appid)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    getMvpView().hideLoading()

                    val gson = Gson()
                    val responses = gson.fromJson<WeatherData.Response>(response.toString(), WeatherData.Response::class.java)

                    if (responses.cod == "200") {
                        getMvpView().onSuccess(responses)
                    } else {
                        getMvpView().onFailed(responses.message.toString())
                    }
                }

                override fun onError(error: ANError) {
                    getMvpView().hideLoading()
                    // handle error
                    error.printStackTrace()
                    getMvpView().onFailed(error.errorCode.toString())
                }
            })
    }
}