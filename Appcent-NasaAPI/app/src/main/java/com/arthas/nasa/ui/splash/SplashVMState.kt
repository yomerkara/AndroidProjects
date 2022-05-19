package com.arthas.nasa.ui.splash

import com.arthas.nasa.ui.base.VMState

interface SplashVMState : VMState {
    class OpenMainScreen : SplashVMState
    class OpenOnboardingScreen : SplashVMState
}