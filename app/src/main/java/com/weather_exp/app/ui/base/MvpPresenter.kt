package com.weather_exp.app.ui.base

import com.androidnetworking.error.ANError

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: ANError)

    fun setUserAsLoggedOut()
}
