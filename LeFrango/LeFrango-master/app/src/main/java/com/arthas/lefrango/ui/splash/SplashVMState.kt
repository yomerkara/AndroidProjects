package com.arthas.lefrango.ui.splash

import com.arthas.lefrango.ui.base.VMState

interface SplashVMState : VMState {
    class OpenLoginScreen : SplashVMState
    class OpenMainScreen : SplashVMState
    class ControlDeeplink : SplashVMState
    class OpenOnboardingScreen : SplashVMState
}