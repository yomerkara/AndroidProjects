package com.arthas.lefrango.ui.menu.home

import androidx.lifecycle.MutableLiveData
import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.listener.OnSingleClickListener

class HomeViewModel(val sharedPref: SharedPref) : BaseViewModel() {

    val name = MutableLiveData<String>()

    val onExpand = OnSingleClickListener {
        state.value = HomeVMState.OnExpand()
    }
}