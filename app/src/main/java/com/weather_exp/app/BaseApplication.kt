package com.weather_exp.app

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.weather_exp.app.data.prefs.AppPreferences
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class BaseApplication: MultiDexApplication() {

    companion object {
        lateinit var mContext: Context
        lateinit var mInstance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = this

        if (!BuildConfig.DEBUG) {
            //Fabric.with(this, new Crashlytics());
        }

        AppPreferences

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor { chain ->
            //val credential: String = Credentials.basic(ConstantsUtils.BASIC_AUTH_USERNAME, ConstantsUtils.BASIC_AUTH_PASSWORD)
            val request = chain.request().newBuilder()
                //.addHeader("Authorization", credential)
                .build()
            chain.proceed(request)
        }
        AndroidNetworking.initialize(applicationContext, okHttpClient.build())

        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }
    }

    fun getInstance(): BaseApplication {
        return mInstance
    }

    fun getContext(): Context {
        return mContext
    }
}