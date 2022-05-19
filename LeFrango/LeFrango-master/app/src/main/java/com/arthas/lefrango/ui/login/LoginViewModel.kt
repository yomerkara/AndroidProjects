package com.arthas.lefrango.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.data.model.login.LoginRequest
import com.arthas.lefrango.data.repo.LoginRepo
import com.arthas.lefrango.service.util.AppResult
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.ui.signup.SignupVMState
import com.arthas.lefrango.util.extensions.isTCKNCorrect
import com.arthas.lefrango.util.extensions.multiLet
import com.arthas.lefrango.util.listener.OnSingleClickListener
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sharedPref: SharedPref,
    private val loginRepo: LoginRepo
) : BaseViewModel() {

    val tc = MutableLiveData<String>()
    val tcValidationError = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isEnabledLoginButton = ObservableField(false)
    var requiredFields: Array<MutableLiveData<*>>? = null

    init {
        requiredFields = arrayOf(
            tc,
            password
        )
    }

    val onLogin = OnSingleClickListener {
        login()
    }

    val onForgotPassword = OnSingleClickListener {
        state.value = LoginVMState.OpenForgotPasswordScreen()
    }

    val onRegister = OnSingleClickListener {
        state.value = LoginVMState.OpenRegisterScreen()
    }

    val onSignInWithGoogle = OnSingleClickListener {
        state.value = LoginVMState.SignInWithGoogle()
    }

    private fun login() {
        state.value = LoginVMState.OpenMainScreen()
        multiLet(tc.value, password.value) { tc, password ->
            val request = LoginRequest(tc, password)
            viewModelScope.launch {
                when (val result = loginRepo.login(request)) {
                    is AppResult.Success -> {
                        saveUserInfoToSharedPref(
                            result.successData.data.token,
                            result.successData.data.user.name

                        )
                        sharedPref.isVictim = result.successData.data.isVictim
                        state.value = LoginVMState.OpenMainScreen()
                    }
                    is AppResult.Error -> {

                    }
                }
            }
        }
    }

    private fun tcknValidationControl() {
        if (!tc.value.isNullOrEmpty()) {
            if (tc.value?.isTCKNCorrect() == true) {
                tcValidationError.value = ""
            } else
                state.value = SignupVMState.TcknNotCorrectError()
        } else
            state.value = SignupVMState.TcknNotCorrectError()
    }

    private fun saveUserInfoToSharedPref(token: String, name: String) {
        sharedPref.token = token
        sharedPref.name = name
    }


    fun loginButtonValidateControl() {
        tcknValidationControl()
        isEnabledLoginButton.set(
            !tc.value.isNullOrEmpty()
                    && !password.value.isNullOrEmpty()
                    && password.value?.length ?: 0 >= 8
        )
    }

}