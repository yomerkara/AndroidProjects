package com.arthas.nasa.ui.onboarding

import com.arthas.nasa.ui.base.VMState

interface OnboardingVMState : VMState {
    class Skip : OnboardingVMState
    class Continue : OnboardingVMState
}