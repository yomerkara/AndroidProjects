package com.arthas.nasa.module

import com.arthas.nasa.ui.base.BaseViewModel
import com.arthas.nasa.ui.main.MainViewModel
import com.arthas.nasa.ui.menu.curiosity.CuriosityViewModel
import com.arthas.nasa.ui.menu.opportunity.OpportunityViewModel
import com.arthas.nasa.ui.menu.spirit.SpiritViewModel
import com.arthas.nasa.ui.onboarding.OnboardingViewModel
import com.arthas.nasa.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { BaseViewModel() }
    viewModel { SplashViewModel(sharedPref = get()) }
    viewModel { MainViewModel() }
    viewModel { CuriosityViewModel() }
    viewModel { OpportunityViewModel() }
    viewModel { SpiritViewModel() }
    viewModel { OnboardingViewModel(sharedPref = get()) }

}