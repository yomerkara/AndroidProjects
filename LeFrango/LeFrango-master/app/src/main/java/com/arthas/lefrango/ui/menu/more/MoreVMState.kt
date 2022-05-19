package com.arthas.lefrango.ui.menu.more

import com.arthas.lefrango.ui.base.VMState

interface MoreVMState : VMState {
    class OnLogOut : MoreVMState
    class OpenLoginScreen : MoreVMState
    class OnOpenNeed : MoreVMState
}