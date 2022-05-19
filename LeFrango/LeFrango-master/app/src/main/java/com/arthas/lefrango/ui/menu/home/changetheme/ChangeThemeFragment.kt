package com.arthas.lefrango.ui.menu.home.changetheme

import android.app.UiModeManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.SliderRestaurantFoodListBinding
import com.arthas.lefrango.ui.base.BaseFragment
import com.arthas.lefrango.ui.base.VMState
import org.koin.android.ext.android.get

class ChangeThemeFragment : BaseFragment<ChangeThemeViewModel, SliderRestaurantFoodListBinding>() {

    override val layoutRes: Int
        get() = R.layout.slider_restaurant_food_list
    override val viewModel: ChangeThemeViewModel = get()

    override fun initUI(view: View) {

    }

    override fun initListener() {

    }

    override fun onChangedScreenState(state: VMState) {

    }
}