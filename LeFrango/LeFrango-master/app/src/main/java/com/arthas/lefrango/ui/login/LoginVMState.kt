package com.arthas.lefrango.ui.login

import com.arthas.lefrango.ui.base.VMState

interface LoginVMState : VMState {
    class OpenMainScreen : LoginVMState
    class OpenForgotPasswordScreen : LoginVMState
    class OpenRegisterScreen : LoginVMState
    class TcknNotValidError : LoginVMState
    class SignInWithGoogle : LoginVMState
}