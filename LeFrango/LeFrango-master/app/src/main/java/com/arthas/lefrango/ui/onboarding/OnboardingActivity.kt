package com.arthas.lefrango.ui.onboarding

import android.app.Activity
import android.content.Intent
import androidx.viewpager.widget.ViewPager
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.ActivityOnboardingBinding
import com.arthas.lefrango.ui.base.BaseActivity
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.login.LoginActivity
import com.arthas.lefrango.ui.onboarding.adapter.OnboardingAdapter
import com.arthas.lefrango.ui.onboarding.fragments.Onboarding1Fragment
import com.arthas.lefrango.ui.onboarding.fragments.Onboarding2Fragment
import com.arthas.lefrango.ui.onboarding.fragments.Onboarding3Fragment
import org.koin.android.ext.android.get

class OnboardingActivity : BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_onboarding

    override val viewModel: OnboardingViewModel = get()

    override fun initUI() {
        val adapter = OnboardingAdapter(supportFragmentManager)
        adapter.addFragment(Onboarding1Fragment())
        adapter.addFragment(Onboarding2Fragment())
        adapter.addFragment(Onboarding3Fragment())
        binding?.viewPager?.adapter = adapter
        binding?.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                viewModel.currentPagePosition.value = position
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })
        binding?.pageIndicator?.setupWithViewPager(binding?.viewPager)
    }

    override fun initListener() {

    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is OnboardingVMState.Skip -> {
                LoginActivity.open(this)
            }
            is OnboardingVMState.Continue -> {
                LoginActivity.open(this)
            }
        }
    }

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, OnboardingActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

}