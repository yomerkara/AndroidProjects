package com.arthas.lefrango.core

import android.content.Context

class SharedPref constructor(private var context: Context) {

    private val credentialsPreferences: android.content.SharedPreferences
        get() = context.getSharedPreferences(
            Constants.SharedPref.appCredentials,
            Context.MODE_PRIVATE
        )

    private val credentialsPreferencesEditor: android.content.SharedPreferences.Editor
        get() = credentialsPreferences.edit()

    var name: String?
        get() = credentialsPreferences.getString(Constants.SharedPref.email, "")
        set(email) {
            val editor =
                credentialsPreferencesEditor.putString(Constants.SharedPref.email, email)
            editor.commit()
        }

    var token: String?
        get() = credentialsPreferences.getString(Constants.SharedPref.token, "")
        set(token) {
            val editor =
                credentialsPreferencesEditor.putString(Constants.SharedPref.token, token)
            editor.commit()
        }

    var isVictim: Boolean?
        get() = credentialsPreferences.getBoolean(Constants.SharedPref.isVictim, false)
        set(isVictim) {
            val editor =
                isVictim?.let {
                    credentialsPreferencesEditor.putBoolean(
                        Constants.SharedPref.isVictim,
                        it
                    )
                }
            editor?.commit()
        }

    fun clearCredentials() {
        credentialsPreferencesEditor.clear().commit()
    }

}