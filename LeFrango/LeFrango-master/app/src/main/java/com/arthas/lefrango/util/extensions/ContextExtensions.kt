package com.arthas.lefrango.util.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.arthas.lefrango.R
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.service.util.AppResult

fun Context.noNetworkConnectivityError(): AppResult.Error {
    val errorMessage = this.resources.getString(R.string.no_network_connectivity)
    with(Constants.App.lastActivity) {
        this?.runOnUiThread {
            dialog(errorMessage) {
                positiveButton(getStr(R.string.ok)) { }
            }.show()
        }
    }
    return AppResult.Error(Exception(errorMessage))
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: this.window.decorView)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.call(number: String) =
    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:0$number")))

fun Context.getStr(resId: Int) = resources.getString(resId)