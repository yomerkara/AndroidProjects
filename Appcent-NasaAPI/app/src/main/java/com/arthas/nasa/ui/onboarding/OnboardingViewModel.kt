package com.arthas.nasa.ui.onboarding

import androidx.lifecycle.MutableLiveData
import com.arthas.nasa.core.SharedPref
import com.arthas.nasa.ui.base.BaseViewModel
import com.arthas.nasa.util.listener.OnSingleClickListener

class OnboardingViewModel(private val sharedPref: SharedPref) : BaseViewModel() {

    val currentPagePosition = MutableLiveData(0)

    val onSkip = OnSingleClickListener {
        state.value = OnboardingVMState.Skip()
        sharedPref.isFirstLaunchApp = false
    }

    val onContinue = OnSingleClickListener {
        state.value = OnboardingVMState.Continue()
        sharedPref.isFirstLaunchApp = false
    }

}