package com.arthas.nasa.core

import android.content.Context

class SharedPref constructor(private var context: Context) {

    private val credentialsPreferences: android.content.SharedPreferences
        get() = context.getSharedPreferences(
            Constants.SharedPref.appCredentials,
            Context.MODE_PRIVATE
        )

    private val credentialsPreferencesEditor: android.content.SharedPreferences.Editor
        get() = credentialsPreferences.edit()

    var token: String?
        get() = credentialsPreferences.getString(Constants.SharedPref.token, "")
        set(token) {
            val editor =
                credentialsPreferencesEditor.putString(Constants.SharedPref.token, token)
            editor.commit()
        }

    var isFirstLaunchApp: Boolean?
        get() = credentialsPreferences.getBoolean(Constants.SharedPref.isFirstLaunchApp, true)
        set(isFirstLaunchApp) {
            val editor =
                credentialsPreferencesEditor.putBoolean(
                    Constants.SharedPref.isFirstLaunchApp,
                    isFirstLaunchApp ?: true
                )
            editor.commit()
        }

    fun clearCredentials() {
        credentialsPreferencesEditor.clear().commit()
    }

}