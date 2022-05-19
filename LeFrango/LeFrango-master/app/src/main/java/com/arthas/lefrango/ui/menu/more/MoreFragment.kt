package com.arthas.lefrango.ui.menu.more

import android.view.View
import com.arthas.lefrango.R
import com.arthas.lefrango.databinding.FragmentMoreBinding
import com.arthas.lefrango.ui.base.BaseFragment
import com.arthas.lefrango.ui.base.VMState
import com.arthas.lefrango.ui.login.LoginActivity
import com.arthas.lefrango.ui.main.MainActivity
import org.koin.android.ext.android.get

class MoreFragment : BaseFragment<MoreViewModel, FragmentMoreBinding>() {

    override val viewModel: MoreViewModel = get()

    override val layoutRes: Int
        get() = R.layout.fragment_more

    override fun initUI(view: View) {

    }

    override fun initListener() {

    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is MoreVMState.OnLogOut -> {
                viewModel.logOut()
            }
            is MoreVMState.OnOpenNeed -> {
                binding?.containerLogOut?.visibility=View.GONE
                binding?.containerNeeded?.visibility=View.GONE
                binding?.isNeeded?.root?.visibility = View.VISIBLE
            }
            is MoreVMState.OpenLoginScreen -> {
                (activity as MainActivity).let {
                    LoginActivity.open(it)
                }
            }
        }
    }

}