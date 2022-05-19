package com.arthas.lefrango.ui.signup

import com.arthas.lefrango.ui.base.VMState

interface SignupVMState : VMState {
    class ShowUserAgreement : SignupVMState
    class OpenLoginScreen : SignupVMState
    class OnBack : SignupVMState
    class TcknNotCorrectError : SignupVMState
    class BirthDateNotCorrectError : SignupVMState
    class NameIsEmptyError : SignupVMState
    class SurnameIsEmptyError : SignupVMState
}