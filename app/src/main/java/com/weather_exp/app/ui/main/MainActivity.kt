package com.weather_exp.app.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.weather_exp.app.R
import com.weather_exp.app.data.network.model.weather.WeatherData
import com.weather_exp.app.ui.base.BaseActivity
import com.weather_exp.app.ui.main.adapter.WeatherAdapter
import com.weather_exp.app.utils.ConstantsUtils
import com.weather_exp.app.utils.ImageUtils
import com.weather_exp.app.utils.ToastMessageUtils
import kotlinx.android.synthetic.main.main_activity.*

@Suppress("DEPRECATION", "DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")
class MainActivity : BaseActivity(), MainView {

    private var mPresenter : MainPresenter<MainView> = MainPresenter()

    lateinit var mWeatherAdapter: WeatherAdapter

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }

        lateinit var instance: MainActivity
        fun getInstace(): MainActivity {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mPresenter.onAttach(this)

        setUp()
    }

    override fun setUp() {
        setSupportActionBar(toolbar)

        instance = this

        openWeather()
    }

    private fun openWeather(){
        val dataReq = WeatherData.Request()
        dataReq.q = "Bandung, Jawa Barat"
        dataReq.appid = ConstantsUtils.OPENWEATHER_APPID

        tv_cityname.text = dataReq.q
        mPresenter.onWeather(dataReq)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.weather_exp.app.R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_setting -> {

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSuccess(response: WeatherData.Response) {

        showDetail(response.list!![0])

        val mLayoutManager: RecyclerView.LayoutManager
        mWeatherAdapter = WeatherAdapter(this, response.list!!)
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rv_weather.layoutManager = mLayoutManager
        rv_weather.itemAnimator = DefaultItemAnimator()
        rv_weather.adapter = mWeatherAdapter

        mWeatherAdapter.setOnClickListener(object : WeatherAdapter.OnClickListener {

            override fun onDetail(view: View, data: WeatherData.DataList, pos: Int) {
                ToastMessageUtils.message(applicationContext, "selected")
                showDetail(data)
            }
        })
    }

    override fun onFailed(msg: String) {
        Log.i("data response failed", msg)
    }

    private fun showDetail(data: WeatherData.DataList){
        val temperature = String.format(this.getString(R.string.celsius), String.format("%.0f", data.main!!.temp))
        tv_temp.text = temperature

        val wind = data.wind!!.speed.toString()
        tv_wind.text = "$wind km/h"

        val desc = data.weather!![0].description
        tv_desc.text = desc
        //tv_desc.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        val icon = data.weather!![0].icon

        Glide.with(this).load("http://openweathermap.org/img/w/$icon.png")
            .apply(ImageUtils.RequestOption())
            .into(imageView)
    }

}
