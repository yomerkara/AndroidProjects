package com.arthas.nasa.ui.splash

import com.arthas.nasa.core.SharedPref
import com.arthas.nasa.ui.base.BaseViewModel
import kotlinx.coroutines.*

class SplashViewModel(private val sharedPref: SharedPref) : BaseViewModel() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    init {
        activityScope.launch {
            delay(3000)
            openNextScreen()
        }
    }

    private fun openNextScreen() {
        if (sharedPref.isFirstLaunchApp == false) {
            state.value = SplashVMState.OpenMainScreen()
        } else {
            state.value = SplashVMState.OpenOnboardingScreen()
        }


    }

    override fun onCleared() {
        activityScope.cancel()
        super.onCleared()
    }

}