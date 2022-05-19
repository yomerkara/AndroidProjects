package com.arthas.lefrango.ui.forgotpass.email

import com.arthas.lefrango.ui.base.VMState

interface ForgotPassEmailVMState : VMState {
    class ShowCheckMailScreen : ForgotPassEmailVMState
    class OpenLoginScreen : ForgotPassEmailVMState
    class Back : ForgotPassEmailVMState
    class EmailValidationError : ForgotPassEmailVMState
}