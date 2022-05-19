package com.arthas.lefrango.ui.forgotpass.newpass

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arthas.lefrango.R
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.databinding.ActivityForgotPassNewPassBinding
import com.arthas.lefrango.ui.base.BaseActivity
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.login.LoginActivity
import com.arthas.lefrango.util.extensions.getStr
import com.arthas.lefrango.util.extensions.hideKeyboard
import org.koin.android.ext.android.get

class ForgotPassNewPassActivity :
    BaseActivity<ForgotPassNewPassViewModel, ActivityForgotPassNewPassBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_forgot_pass_new_pass

    override val viewModel: ForgotPassNewPassViewModel = get()

    override fun initUI() {
        val intentData = intent.extras
        intentData?.let {
            val key = intentData.getString(Constants.Intent.key)
            val email = intentData.getString(Constants.Intent.email)
            viewModel.setData(key, email)
        }
    }

    override fun initListener() {
        viewModel.requiredFields?.forEach {
            it.observe(this, {
                viewModel.createPassButtonValidateControl()
            })
        }
    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is ForgotPassNewPassVMState.ShowSuccessScreen -> {
                binding?.container?.visibility = View.INVISIBLE
                binding?.layoutForgotPassSuccess?.container?.visibility = View.VISIBLE
                hideKeyboard()
            }
            is ForgotPassNewPassVMState.OpenLoginScreen -> {
                LoginActivity.open(this)
            }
            is ForgotPassNewPassVMState.Back -> {
                finish()
            }
            is ForgotPassNewPassVMState.PasswordValidationError -> {
                viewModel.passwordValidationError.value = getStr(R.string.password_match_error)
            }
        }
    }

    companion object {
        fun open(activity: Activity, key: String, email: String) {
            val intent = Intent(activity, ForgotPassNewPassActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Constants.Intent.key, key)
            bundle.putString(Constants.Intent.email, email)
            intent.putExtras(bundle)
            activity.startActivity(intent)
            activity.finish()
        }
    }

}