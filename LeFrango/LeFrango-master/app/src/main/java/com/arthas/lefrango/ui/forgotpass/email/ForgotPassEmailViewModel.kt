package com.arthas.lefrango.ui.forgotpass.email

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.arthas.lefrango.data.repo.LoginRepo
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.extensions.isValidEmail
import com.arthas.lefrango.util.listener.OnSingleClickListener

class ForgotPassEmailViewModel(/*private val loginRepo: LoginRepo*/) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val emailValidationError = MutableLiveData<String>()
    val isEnabledSendButton = ObservableField(false)
    var requiredFields: Array<MutableLiveData<*>>? = null

    init {
        requiredFields = arrayOf(
            email
        )
    }

    val onSend = OnSingleClickListener {
        sendEmail()
    }

    val onBackToTheLogin = OnSingleClickListener {
        state.value = ForgotPassEmailVMState.OpenLoginScreen()
    }

    val onBack = OnSingleClickListener {
        state.value = ForgotPassEmailVMState.Back()
    }

    private fun sendEmail() {

    }

    private fun emailValidateControl() {
        if (!email.value.isNullOrEmpty()) {
            if (email.value?.isValidEmail() == true)
                emailValidationError.value = ""
            else
                state.value = ForgotPassEmailVMState.EmailValidationError()
        }
    }

    fun sendButtonValidateControl() {
        emailValidateControl()
        isEnabledSendButton.set(
            !email.value.isNullOrEmpty()
                    && emailValidationError.value.isNullOrEmpty()
        )
    }

}