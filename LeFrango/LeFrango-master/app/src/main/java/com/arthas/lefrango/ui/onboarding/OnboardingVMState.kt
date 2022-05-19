package com.arthas.lefrango.ui.onboarding

import com.arthas.lefrango.ui.base.VMState

interface OnboardingVMState : VMState {
    class Skip : OnboardingVMState
    class Continue : OnboardingVMState
}