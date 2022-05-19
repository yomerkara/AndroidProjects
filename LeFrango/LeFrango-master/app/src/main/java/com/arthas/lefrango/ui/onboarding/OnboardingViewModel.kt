package com.arthas.lefrango.ui.onboarding

import androidx.lifecycle.MutableLiveData
import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.listener.OnSingleClickListener

class OnboardingViewModel(private val sharedPref: SharedPref) : BaseViewModel() {

    val currentPagePosition = MutableLiveData(0)

    val onSkip = OnSingleClickListener {
        state.value = OnboardingVMState.Skip()
    }

    val onContinue = OnSingleClickListener {
        state.value = OnboardingVMState.Continue()
    }

}