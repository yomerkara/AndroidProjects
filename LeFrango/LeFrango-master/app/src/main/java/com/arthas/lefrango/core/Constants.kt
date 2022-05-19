package com.arthas.lefrango.core

import com.arthas.lefrango.ui.base.BaseActivity

class Constants {

    object App {
        const val appCenterId = ""
        var lastActivity: BaseActivity<*, *>? = null
    }

    object SharedPref {
        const val appCredentials = "AppCredentials"
        const val email = "email"
        const val token = "token"
        const val isVictim = "isVictim"
        const val sendID = "sendID"
        const val receiveID = "receiveID"
        const val openGoogle = "Opening Google.."
        const val openSearch = "Searching.."
    }

    object Deeplink {
        const val resetPassword = "resetPassword"
        const val register = "register"
        const val key = "key"
        const val email = "mail"
    }

    object Intent {
        const val email = "email"
        const val key = "key"
    }

}