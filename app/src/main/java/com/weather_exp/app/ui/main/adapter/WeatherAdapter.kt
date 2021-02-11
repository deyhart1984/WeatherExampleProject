package com.weather_exp.app.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.weather_exp.app.R
import com.weather_exp.app.data.network.model.weather.WeatherData
import com.weather_exp.app.utils.DateUtils.getDayFromDateString
import com.weather_exp.app.utils.ImageUtils
import com.weather_exp.app.utils.ToastMessageUtils
import java.text.SimpleDateFormat
import java.util.*


class WeatherAdapter(private val mContext: Context, datas: MutableList<WeatherData.DataList>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var mDatas = ArrayList<WeatherData.DataList>(datas)

    private var mOnClickListener: OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener) {
        mOnClickListener = onClickListener
        Log.i("TEST", "CLICK BRO")
        //ToastMessageUtils.message(mContext, "selected click")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context) .inflate(R.layout.weather_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mDatas[position]

        val iconName = data.weather!![0].icon
        val temp = data.main!!.temp

        val day = getDayFromDateString(data.dt_txt!!, "yyyy-MM-dd HH:mm:ss", false)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = formatter.parse(data.dt_txt)
        val desiredFormat = SimpleDateFormat("dd").format(date)

        holder.mTvDate.text = "$day, $desiredFormat"

        val temperature = String.format(mContext.getString(R.string.celsius), String.format("%.0f", temp))
        holder.mTvTemp.text = temperature

        Glide.with(mContext).load("http://openweathermap.org/img/w/$iconName.png")
            .apply(ImageUtils.RequestOption())
            .into(holder.mIcon)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTvDate: TextView = view.findViewById<View>(R.id.tv_date) as TextView
        var mTvTemp: TextView = view.findViewById<View>(R.id.tv_temperature) as TextView
        var mIcon: ImageView = view.findViewById<View>(R.id.icon) as ImageView
        var mView: View = view

    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    interface OnClickListener {
        fun onDetail(view: View, data: WeatherData.DataList, pos: Int)
    }

}