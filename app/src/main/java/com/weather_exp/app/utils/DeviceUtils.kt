package com.weather_exp.app.utils

import android.content.Context
import android.provider.Settings

object DeviceUtils {
    fun getId(context: Context): String {
        return Settings.Secure.getString(
            context.contentResolver, Settings.Secure.ANDROID_ID
        )
    }
}
