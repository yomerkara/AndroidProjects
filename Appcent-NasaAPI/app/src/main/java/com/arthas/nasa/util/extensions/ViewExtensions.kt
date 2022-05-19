package com.arthas.nasa.util.extensions

import android.view.View
import androidx.databinding.BindingAdapter
import com.arthas.nasa.util.listener.OnSingleClickListener

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(onSingleClickListener: OnSingleClickListener) {
    setOnClickListener(onSingleClickListener)
}

fun View.toggleVisibility() {
    this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}