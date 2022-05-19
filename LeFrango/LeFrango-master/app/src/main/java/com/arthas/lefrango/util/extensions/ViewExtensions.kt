package com.arthas.lefrango.util.extensions

import android.view.View
import androidx.databinding.BindingAdapter
import com.arthas.lefrango.util.listener.OnSingleClickListener

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(onSingleClickListener: OnSingleClickListener) {
    setOnClickListener(onSingleClickListener)
}

fun View.toggleVisibility() {
    this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}