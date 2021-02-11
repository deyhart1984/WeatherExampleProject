package com.weather_exp.app.ui.base

import android.content.Context
import android.support.annotation.StringRes

interface MvpView {
    /*fun showLoading()

    fun hideLoading()

    fun showLocationDialog()

    fun hideLocationDialog()

    fun showDownloadDialog()

    fun updateDownloadDialog(progress: Int)

    fun hideDownloadDialog()*/

    fun onError(message: String)

    fun onError(@StringRes resId: Int)

    fun showMessage(message: String)

    fun showMessage(@StringRes resId: Int)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()

    fun getContext(): Context

    fun showDialogMessage(message: String)

    fun showLoading()

    fun hideLoading()
}