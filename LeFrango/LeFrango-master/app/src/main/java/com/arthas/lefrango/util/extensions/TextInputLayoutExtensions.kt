package com.arthas.lefrango.util.extensions

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.addRegex(regex: Regex) {
    this.editText?.addTextChangedListener {
        val result = regex.replace(it.toString(), "")
        if (it.toString() != result) {
            this.editText?.setText(result)
            this.editText?.setSelection(result.length)
        }
    }
}