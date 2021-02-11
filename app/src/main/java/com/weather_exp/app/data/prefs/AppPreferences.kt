package com.weather_exp.app.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.weather_exp.app.utils.ConstantsUtils
import com.weather_exp.app.BaseApplication.Companion.mContext

class AppPreferences {
    companion object {
        var mPrefs: SharedPreferences = mContext.getSharedPreferences(ConstantsUtils.PREF_NAME, Context.MODE_PRIVATE)

        fun isLogin(): Boolean {
            return mPrefs.getBoolean(ConstantsUtils.PREF_LOGIN_STATUS, false);
        }

        fun setLogin(login: Boolean) {
            mPrefs.edit().putBoolean(ConstantsUtils.PREF_LOGIN_STATUS, login).apply();
        }


    }
}
