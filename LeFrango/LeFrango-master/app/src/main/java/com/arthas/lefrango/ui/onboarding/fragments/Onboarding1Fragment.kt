package com.arthas.lefrango.ui.onboarding.fragments

import android.view.View
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.FragmentOnboarding1Binding
import com.arthas.lefrango.ui.base.BaseFragment
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.onboarding.OnboardingViewModel
import org.koin.android.ext.android.get

class Onboarding1Fragment : BaseFragment<OnboardingViewModel, FragmentOnboarding1Binding>() {

    override val viewModel: OnboardingViewModel = get()

    override val layoutRes: Int
        get() = R.layout.fragment_onboarding1

    override fun initListener() {

    }

    override fun onChangedScreenState(state: VMState) {

    }

    override fun initUI(view: View) {

    }

}