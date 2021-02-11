package com.weather_exp.app.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getCurrentDate(): Date {
        val calendar = Calendar.getInstance().time
        return calendar
    }

    fun getDayFromDateString(stringDate: String, dateTimeFormat: String, isFullLabel: Boolean): String {
        var daysArray = arrayOf("Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab")
        if(isFullLabel){
            daysArray = arrayOf("Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu")
        }
        var day = ""

        var dayOfWeek = 0
        //dateTimeFormat = yyyy-MM-dd HH:mm:ss
        val formatter = SimpleDateFormat(dateTimeFormat)
        val date: Date
        try {
            date = formatter.parse(stringDate)
            val c = Calendar.getInstance()
            c.time = date
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1
            if (dayOfWeek < 0) {
                dayOfWeek += 7
            }
            day = daysArray[dayOfWeek]
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return day
    }
}