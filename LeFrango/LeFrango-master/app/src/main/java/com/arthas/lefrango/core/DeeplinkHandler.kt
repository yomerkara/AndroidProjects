package com.arthas.lefrango.core

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.arthas.lefrango.ui.forgotpass.newpass.ForgotPassNewPassActivity
import com.arthas.lefrango.ui.login.LoginActivity

class DeeplinkHandler constructor(private val activity: Activity, private val intent: Intent) {

    fun handleIntent(): Boolean {
        val appLinkAction: String? = intent.action
        val appLinkData: Uri? = intent.data
        val appLinkPath: String? = appLinkData?.path?.replace("/", "")
        return if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null && appLinkPath != null) {
            showDeepLinkOffer(appLinkData, appLinkPath)
            true
        } else {
            false
        }
    }

    private fun showDeepLinkOffer(
        appLinkData: Uri,
        appLinkPath: String
    ) {
        when (appLinkPath) {
            Constants.Deeplink.resetPassword -> {
                val key = appLinkData.getQueryParameter(Constants.Deeplink.key) ?: ""
                val email = appLinkData.getQueryParameter(Constants.Deeplink.email) ?: ""
                if (key.isNotEmpty() && email.isNotEmpty()) {
                    ForgotPassNewPassActivity.open(activity, key, email)
                }
            }
            Constants.Deeplink.register -> {
                val key = appLinkData.getQueryParameter(Constants.Deeplink.key) ?: ""
                val email = appLinkData.getQueryParameter(Constants.Deeplink.email) ?: ""
                if (key.isNotEmpty() && email.isNotEmpty()) {
                    LoginActivity.open(activity, key, email)
                }
            }
        }
    }

}
