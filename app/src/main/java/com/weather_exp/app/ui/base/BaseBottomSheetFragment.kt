package com.weather_exp.app.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.BottomSheetDialogFragment

import android.view.View

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment(), MvpView {

    private var baseActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
            activity!!.onFragmentAttached()
        }
    }

    protected abstract fun setUp(view: View)

    override fun onError(message: String) {
        if (baseActivity != null) {
            baseActivity!!.onError(message)
        }
    }

    override fun onError(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.onError(resId)
        }
    }

    override fun showMessage(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(resId)
        }
    }

    override fun isNetworkConnected(): Boolean {
        return if (baseActivity != null) {
            baseActivity!!.isNetworkConnected()
        } else false
    }

    override fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    override fun getContext(): Context {
        if(baseActivity != null) {
            return baseActivity!!.getContext()
        }else{
            return requireContext()
        }
    }

    override fun showDialogMessage(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showDialogMessage(message)
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}
