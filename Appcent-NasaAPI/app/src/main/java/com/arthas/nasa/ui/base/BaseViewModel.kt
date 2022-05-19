package com.arthas.nasa.ui.base

import androidx.lifecycle.ViewModel
import com.arthas.nasa.util.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    val state = SingleLiveEvent<VMState>()

}