package com.arthas.lefrango.ui.signup

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.data.model.signup.RegisterRequest
import com.arthas.lefrango.data.repo.LoginRepo
import com.arthas.lefrango.service.util.AppResult
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.RegexUtils
import com.arthas.lefrango.util.extensions.isTCKNCorrect
import com.arthas.lefrango.util.listener.OnSingleClickListener
import kotlinx.coroutines.launch

class SignupViewModel(val loginRepo: LoginRepo, val sharedPref: SharedPref) : BaseViewModel() {

    val tc = MutableLiveData<String>()
    val birthDate = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val is8digits = ObservableField(false)
    val name = MutableLiveData<String>()
    val surname = MutableLiveData<String>()
    val birthDateValidationError = MutableLiveData<String>()
    val tcValidationError = MutableLiveData<String>()
    val nameEmptyError = MutableLiveData<String>()
    val surNameEmptyError = MutableLiveData<String>()
    val isAcceptUserAgreement = MutableLiveData<Boolean>()
    val isEnabledRegisterButton = ObservableField(false)
    var requiredFields: Array<MutableLiveData<*>>? = null

    init {
        requiredFields = arrayOf(
            tc,
            birthDate,
            name,
            surname,
            password,
            isAcceptUserAgreement
        )
    }

    val onRegister = OnSingleClickListener {
        register()
    }

    val onUserAgreement = OnSingleClickListener {
        state.value = SignupVMState.ShowUserAgreement()
    }

    val onLogin = OnSingleClickListener {
        state.value = SignupVMState.OpenLoginScreen()
    }

    val onBack = OnSingleClickListener {
        state.value = SignupVMState.OnBack()
    }

    private fun register() {
        val request = tc.value?.let {
            name.value?.let { it1 ->
                surname.value?.let { it2 ->
                    birthDate.value?.let { it3 ->
                        password.value?.let { it4 ->
                            RegisterRequest(
                                tc = it,
                                name = it1,
                                surName = it2,
                                birthDate = it3,
                                password = it4
                            )
                        }
                    }
                }
            }
        }
        viewModelScope.launch {
            when (val result = request?.let { loginRepo.register(it) }) {
                is AppResult.Success -> {
                    state.value = SignupVMState.OpenLoginScreen()
                    sharedPref.token = result.successData.data.token
                }
                is AppResult.Error -> {

                }
            }
        }

    }

    fun registerButtonValidateControl() {
        birthDateValidateControl()
        passwordValidateControl()
        nameValidationControl()
        surnameValidationControl()
        tcknValidationControl()

        isEnabledRegisterButton.set(
            tc.value?.isTCKNCorrect() == true
                    && !password.value.isNullOrEmpty()
                    && !birthDate.value.isNullOrEmpty() || birthDate.value != "--/--/----"
                    && !name.value.isNullOrEmpty()
                    && !surname.value.isNullOrEmpty()
                    && is8digits.get() == true
                    && isAcceptUserAgreement.value == true
        )
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

    private fun surnameValidationControl() {
        if (!surname.value.isNullOrEmpty())
            surNameEmptyError.value = ""
        else
            state.value = SignupVMState.SurnameIsEmptyError()
    }

    private fun nameValidationControl() {
        if (!name.value.isNullOrEmpty())
            nameEmptyError.value = ""
        else
            state.value = SignupVMState.NameIsEmptyError()
    }

    fun birthDateValidateControl() {
        if (!birthDate.value.isNullOrEmpty()
            && birthDate.value?.contains(RegexUtils.date) == false
        ) {
            birthDateValidationError.value = ""
        } else {
            state.value = SignupVMState.BirthDateNotCorrectError()
        }

    }

    private fun passwordValidateControl() {
        if (!password.value.isNullOrEmpty())
            is8digits.set(password.value?.length ?: 0 >= 8 && password.value?.length ?: 0 >= 8)
    }

}