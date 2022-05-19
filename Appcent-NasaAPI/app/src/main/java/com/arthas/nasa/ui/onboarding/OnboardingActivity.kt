package com.arthas.nasa.ui.onboarding

import android.app.Activity
import android.content.Intent
import androidx.viewpager.widget.ViewPager
import com.arthas.nasa.R
import com.arthas.nasa.databinding.ActivityOnboardingBinding
import com.arthas.nasa.ui.base.BaseActivity
import com.arthas.nasa.ui.base.VMState
import com.arthas.nasa.ui.main.MainActivity
import com.arthas.nasa.ui.onboarding.adapter.OnboardingAdapter
import com.arthas.nasa.ui.onboarding.fragments.Onboarding1Fragment
import com.arthas.nasa.ui.onboarding.fragments.Onboarding2Fragment
import org.koin.android.ext.android.get

class OnboardingActivity : BaseActivity<OnboardingViewModel, ActivityOnboardingBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_onboarding

    override val viewModel: OnboardingViewModel = get()

    override fun initUI() {
        val adapter = OnboardingAdapter(supportFragmentManager)
        adapter.addFragment(Onboarding1Fragment())
        adapter.addFragment(Onboarding2Fragment())
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
                MainActivity.open(this)
            }
            is OnboardingVMState.Continue -> {
                MainActivity.open(this)
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