package com.arthas.lefrango.ui.forgotpass.newpass

import com.arthas.lefrango.ui.base.VMState

interface ForgotPassNewPassVMState : VMState {
    class ShowSuccessScreen : ForgotPassNewPassVMState
    class OpenLoginScreen : ForgotPassNewPassVMState
    class Back : ForgotPassNewPassVMState
    class PasswordValidationError : ForgotPassNewPassVMState
}