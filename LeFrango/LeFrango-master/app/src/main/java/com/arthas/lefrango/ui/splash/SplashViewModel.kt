package com.arthas.lefrango.ui.splash

import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.ui.base.BaseViewModel
import kotlinx.coroutines.*

class SplashViewModel(private val sharedPref: SharedPref) : BaseViewModel() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    init {
        activityScope.launch {
            delay(3000)
            state.value = SplashVMState.ControlDeeplink()
        }
    }

    fun openNextScreen() {
        state.value =
            if (sharedPref.token.isNullOrEmpty()) {
                SplashVMState.OpenOnboardingScreen()
                //SplashVMState.OpenLoginScreen()
            } else {
                SplashVMState.OpenMainScreen()
            }
    }

    override fun onCleared() {
        activityScope.cancel()
        super.onCleared()
    }

}