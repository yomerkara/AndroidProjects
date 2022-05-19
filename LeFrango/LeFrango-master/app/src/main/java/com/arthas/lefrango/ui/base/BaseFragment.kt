package com.arthas.lefrango.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.arthas.lefrango.ui.dialog.ProgressLoadingDialog
import com.arthas.lefrango.util.extensions.hideKeyboard

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    private val progress: ProgressLoadingDialog by lazy {
        ProgressLoadingDialog(context = requireContext())
    }

    protected var binding: DB? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract val viewModel: VM

    protected abstract fun initUI(view: View)

    protected abstract fun initListener()

    protected abstract fun onChangedScreenState(state: VMState)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        }
        binding?.lifecycleOwner = this
        binding?.setVariable(BR.viewModel, viewModel)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(this, {
            onChangedScreenState(it)
        })

        initUI(view)
        initListener()
    }

    fun showLoading() {
        hideKeyboard()
        progress.toggle(status = true)
    }

    fun hideLoading() {
        progress.release()
    }

}