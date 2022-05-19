package com.arthas.lefrango.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.ui.dialog.ProgressLoadingDialog
import com.arthas.lefrango.util.extensions.hideKeyboard

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    private val progress: ProgressLoadingDialog by lazy {
        ProgressLoadingDialog(context = this)
    }

    protected var binding: DB? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract val viewModel: VM

    @UiThread
    protected abstract fun initUI()

    protected abstract fun initListener()

    protected abstract fun onChangedScreenState(state: VMState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding?.setVariable(BR.viewModel, viewModel)
        binding?.lifecycleOwner = this

        viewModel.state.observe(this, {
            onChangedScreenState(it)
        })

        initUI()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        Constants.App.lastActivity = this
    }

    fun showLoading() {
        runOnUiThread {
            hideKeyboard()
            progress.toggle(status = true)
        }
    }

    fun hideLoading() {
        runOnUiThread {
            progress.release()
        }
    }

}