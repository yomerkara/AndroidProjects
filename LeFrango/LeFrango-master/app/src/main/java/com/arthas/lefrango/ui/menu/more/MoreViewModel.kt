package com.arthas.lefrango.ui.menu.more

import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.listener.OnSingleClickListener

class MoreViewModel(val sharedPref: SharedPref) : BaseViewModel() {

    val onLogOut = OnSingleClickListener {
        state.value = MoreVMState.OnLogOut()
    }

    val onOpenNeed = OnSingleClickListener {
        state.value = MoreVMState.OnOpenNeed()
    }

    fun logOut() {
        sharedPref.clearCredentials()
        state.value = MoreVMState.OpenLoginScreen()
    }
}