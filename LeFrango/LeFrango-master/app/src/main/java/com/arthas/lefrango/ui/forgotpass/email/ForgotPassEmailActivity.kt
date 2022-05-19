package com.arthas.lefrango.ui.forgotpass.email

import android.app.Activity
import android.content.Intent
import android.view.View
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.ActivityForgotPassEmailBinding
import com.arthas.lefrango.ui.base.BaseActivity
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.login.LoginActivity
import com.arthas.lefrango.util.extensions.getStr
import com.arthas.lefrango.util.extensions.hideKeyboard
import org.koin.android.ext.android.get

class ForgotPassEmailActivity :
    BaseActivity<ForgotPassEmailViewModel, ActivityForgotPassEmailBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_forgot_pass_email

    override val viewModel: ForgotPassEmailViewModel = get()

    override fun initUI() {

    }

    override fun initListener() {
        viewModel.requiredFields?.forEach {
            it.observe(this, {
                viewModel.sendButtonValidateControl()
            })
        }
    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is ForgotPassEmailVMState.ShowCheckMailScreen -> {
                binding?.container?.visibility = View.INVISIBLE
                binding?.layoutForgotPassCheckMail?.container?.visibility = View.VISIBLE
                hideKeyboard()
            }
            is ForgotPassEmailVMState.OpenLoginScreen -> {
                LoginActivity.open(this)
            }
            is ForgotPassEmailVMState.Back -> {
                finish()
            }
            is ForgotPassEmailVMState.EmailValidationError -> {
                viewModel.emailValidationError.value = getStr(R.string.email_validation_error)
            }
        }
    }

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, ForgotPassEmailActivity::class.java)
            activity.startActivity(intent)
        }
    }

}