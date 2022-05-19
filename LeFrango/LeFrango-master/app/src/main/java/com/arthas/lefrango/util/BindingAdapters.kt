package com.arthas.lefrango.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: ObservableField<Boolean>) {
    view.visibility = if (isVisible.get() == true) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: MutableLiveData<Boolean>) {
    view.visibility = if (isVisible.value == true) View.VISIBLE else View.GONE
}

@BindingAdapter("setEnabled")
fun setEnabled(view: View, isEnabled: ObservableField<Boolean>) {
    view.isEnabled = isEnabled.get() ?: false
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("circleImageUrl")
fun loadCircleImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(url).circleCrop().into(view)
    }
}

@BindingAdapter("errorText")
fun setErrorText(view: TextInputLayout, errorMessage: MutableLiveData<String>) {
    if (errorMessage.value?.isEmpty() == true)
        view.error = null
    else
        view.error = errorMessage.value
}