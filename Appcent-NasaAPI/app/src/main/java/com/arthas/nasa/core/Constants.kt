package com.arthas.nasa.core

import com.arthas.nasa.ui.base.BaseActivity

class Constants {

    object App {
        const val appCenterId = ""
        var lastActivity: BaseActivity<*, *>? = null
    }

    object SharedPref {
        const val appCredentials = "AppCredentials"
        const val token = "token"
        const val isFirstLaunchApp = "isFirstLaunchApp"
    }

}