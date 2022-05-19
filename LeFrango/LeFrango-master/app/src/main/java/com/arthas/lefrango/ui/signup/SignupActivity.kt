package com.arthas.lefrango.ui.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.ActivitySignupBinding
import com.arthas.lefrango.ui.base.BaseActivity
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.login.LoginActivity
import com.arthas.lefrango.ui.main.MainActivity
import com.arthas.lefrango.util.RegexUtils
import com.arthas.lefrango.util.extensions.addDateFormatterListener
import com.arthas.lefrango.util.extensions.addRegex
import com.arthas.lefrango.util.extensions.getStr
import com.arthas.lefrango.util.extensions.isTCKNCorrect
import org.koin.android.ext.android.get

class SignupActivity : BaseActivity<SignupViewModel, ActivitySignupBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_signup

    override val viewModel: SignupViewModel = get()

    override fun initUI() {
        binding?.tilName?.addRegex(RegexUtils.charactersWithTurkishAndSpace)
        binding?.tilSurname?.addRegex(RegexUtils.charactersWithTurkishAndSpace)
    }

    override fun initListener() {
        binding?.etBirthDate?.addDateFormatterListener()
        viewModel.requiredFields?.forEach {
            it.observe(this, {
                viewModel.registerButtonValidateControl()
            })
        }

    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is SignupVMState.ShowUserAgreement -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://www.google.com")
                startActivity(intent)
            }
            is SignupVMState.OpenLoginScreen -> {
                viewModel.sharedPref.name = viewModel.name.value
                MainActivity.open(this)
            }
            is SignupVMState.BirthDateNotCorrectError -> {
                binding?.etBirthDate?.onFocusChangeListener =
                    View.OnFocusChangeListener { view, hasFocus ->
                        if (!hasFocus) {
                            if (viewModel.birthDate.value.isNullOrEmpty()
                                || viewModel.birthDate.value?.contains(RegexUtils.date) == true
                            ) {
                                viewModel.birthDateValidationError.value =
                                    getStr(R.string.birth_date_not_valid)
                            }

                        }
                    }
            }
            is SignupVMState.NameIsEmptyError -> {
                viewModel.nameEmptyError.value = getStr(R.string.name_is_empty)
            }
            is SignupVMState.SurnameIsEmptyError -> {
                viewModel.surNameEmptyError.value = getStr(R.string.surname_is_empty)
            }
            is SignupVMState.TcknNotCorrectError -> {
                binding?.etTC?.onFocusChangeListener =
                    View.OnFocusChangeListener { view, hasFocus ->
                        if (!hasFocus) {
                            if (!viewModel.tc.value?.isTCKNCorrect()!!)
                                viewModel.tcValidationError.value =
                                    getStr(R.string.tckn_not_valid)
                        }
                    }

            }
            is SignupVMState.OnBack -> {
                LoginActivity.open(this)
            }
        }
    }

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, SignupActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

}