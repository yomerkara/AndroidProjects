package com.arthas.lefrango.ui.splash

import com.arthas.lefrango.R
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.core.DeeplinkHandler
import com.arthas.lefrango.databinding.ActivitySplashBinding
import com.arthas.lefrango.ui.base.BaseActivity
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.login.LoginActivity
import com.arthas.lefrango.ui.main.MainActivity
import com.arthas.lefrango.ui.onboarding.OnboardingActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import org.koin.android.ext.android.get

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_splash

    override val viewModel: SplashViewModel = get()

    override fun initUI() {
        AppCenter.start(
            application, Constants.App.appCenterId,
            Analytics::class.java, Crashes::class.java
        )
        binding?.ltSplashAnimation?.animate()?.translationY(1400f)?.setDuration(1500)?.startDelay =
            4000
    }

    override fun initListener() {

    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is SplashVMState.OpenOnboardingScreen -> {
                OnboardingActivity.open(this)
            }
            is SplashVMState.OpenLoginScreen -> {
                LoginActivity.open(this)
            }
            is SplashVMState.OpenMainScreen -> {
                MainActivity.open(this)
            }
            is SplashVMState.ControlDeeplink -> {
                val hasDeeplink = DeeplinkHandler(this, intent).handleIntent()
                if (!hasDeeplink) {
                    viewModel.openNextScreen()
                }
            }
        }
    }

}