package com.arthas.lefrango.module

import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.ui.forgotpass.email.ForgotPassEmailViewModel
import com.arthas.lefrango.ui.forgotpass.newpass.ForgotPassNewPassViewModel
import com.arthas.lefrango.ui.login.LoginViewModel
import com.arthas.lefrango.ui.main.MainViewModel
import com.arthas.lefrango.ui.menu.details.DetailsViewModel
import com.arthas.lefrango.ui.menu.home.HomeViewModel
import com.arthas.lefrango.ui.menu.more.MoreViewModel
import com.arthas.lefrango.ui.onboarding.OnboardingViewModel
import com.arthas.lefrango.ui.signup.SignupViewModel
import com.arthas.lefrango.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { BaseViewModel() }
    viewModel { SplashViewModel(sharedPref = get()) }
    viewModel { LoginViewModel(sharedPref = get(), loginRepo = get()) }
    viewModel { SignupViewModel(loginRepo = get(), sharedPref = get()) }
    viewModel { ForgotPassEmailViewModel(/*loginRepo = get()*/) }
    viewModel { ForgotPassNewPassViewModel(/*loginRepo = get()*/) }
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(sharedPref = get()) }
    viewModel { DetailsViewModel(sharedPref = get(), loginRepo = get()) }
    viewModel { MoreViewModel(sharedPref = get()) }
    viewModel { OnboardingViewModel(sharedPref = get()) }

}