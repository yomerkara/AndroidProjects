package com.arthas.lefrango.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arthas.lefrango.R
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.databinding.ActivityLoginBinding
import com.arthas.lefrango.ui.base.BaseActivity
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.forgotpass.email.ForgotPassEmailActivity
import com.arthas.lefrango.ui.main.MainActivity
import com.arthas.lefrango.ui.signup.SignupActivity
import com.arthas.lefrango.util.extensions.getStr
import com.arthas.lefrango.util.extensions.isValidEmail
import org.koin.android.ext.android.get

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_login

    override val viewModel: LoginViewModel = get()

    override fun initUI() {
        val intentData = intent.extras
        intentData?.let {
            val key = intentData.getString(Constants.Intent.key)
            val email = intentData.getString(Constants.Intent.email)
        }
    }

    override fun initListener() {
        viewModel.requiredFields?.forEach {
            it.observe(this, {
                viewModel.loginButtonValidateControl()
            })
        }
    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is LoginVMState.OpenMainScreen -> {
                MainActivity.open(this)
            }
            is LoginVMState.OpenForgotPasswordScreen -> {
                ForgotPassEmailActivity.open(this)
            }
            is LoginVMState.OpenRegisterScreen -> {
                SignupActivity.open(this)
            }
            is LoginVMState.TcknNotValidError -> {
                binding?.etTC?.onFocusChangeListener =
                    View.OnFocusChangeListener { view, hasFocus ->
                        if (!hasFocus) {
                            if (viewModel.tc.value?.isValidEmail() == false) {
                                viewModel.tcValidationError.value =
                                    getStr(R.string.tckn_not_valid)
                            }
                        }
                    }

            }

        }
    }

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

        fun open(activity: Activity, key: String, email: String) {
            val intent = Intent(activity, LoginActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Constants.Intent.key, key)
            bundle.putString(Constants.Intent.email, email)
            intent.putExtras(bundle)
            activity.startActivity(intent)
            activity.finish()
        }
    }

}