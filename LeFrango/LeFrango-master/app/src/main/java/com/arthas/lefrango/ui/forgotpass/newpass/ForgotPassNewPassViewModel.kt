package com.arthas.lefrango.ui.forgotpass.newpass

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.arthas.lefrango.data.repo.LoginRepo
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.listener.OnSingleClickListener

class ForgotPassNewPassViewModel(/*private val loginRepo: LoginRepo*/) : BaseViewModel() {

    private val key = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordAgain = MutableLiveData<String>()
    val passwordValidationError = MutableLiveData<String>()
    val is8digits = ObservableField(false)
    val isEnabledCreatePassButton = ObservableField(false)
    var requiredFields: Array<MutableLiveData<*>>? = null

    init {
        requiredFields = arrayOf(
            password,
            passwordAgain
        )
    }

    fun setData(key: String? = "", email: String? = "") {
        this.key.value = key
        this.email.value = email
    }

    val onCreatePass = OnSingleClickListener {
        resetPass()
    }

    val onBackToTheLogin = OnSingleClickListener {
        state.value = ForgotPassNewPassVMState.OpenLoginScreen()
    }

    val onBack = OnSingleClickListener {
        state.value = ForgotPassNewPassVMState.Back()
    }

    private fun resetPass() {

    }

    private fun passwordValidateControl() {
        if (!password.value.isNullOrEmpty() && !passwordAgain.value.isNullOrEmpty()) {
            if (password.value == passwordAgain.value)
                passwordValidationError.value = ""
            else
                state.value = ForgotPassNewPassVMState.PasswordValidationError()
            is8digits.set(password.value?.length ?: 0 >= 8)
        }
    }

    fun createPassButtonValidateControl() {
        passwordValidateControl()
        isEnabledCreatePassButton.set(
            !password.value.isNullOrEmpty()
                    && !passwordAgain.value.isNullOrEmpty()
                    && passwordValidationError.value.isNullOrEmpty()
                    && is8digits.get() == true
        )
    }

}