package com.arthas.lefrango.ui.base

import androidx.lifecycle.ViewModel
import com.arthas.lefrango.util.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    val state = SingleLiveEvent<VMState>()

}