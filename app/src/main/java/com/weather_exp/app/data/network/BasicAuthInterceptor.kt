package com.weather_exp.app.data.network

import android.util.Log
import com.weather_exp.app.utils.ConstantsUtils
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

import java.io.IOException

class BasicAuthInterceptor : Interceptor {
    val credentials: String = Credentials.basic(ConstantsUtils.BASIC_AUTH_USERNAME, ConstantsUtils.BASIC_AUTH_PASSWORD)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials).build()
        Log.d("REQUEST INFO", request.toString())
        Log.d("RESPONSE INFO", chain.proceed(authenticatedRequest).toString())
        return chain.proceed(authenticatedRequest)
    }
}
