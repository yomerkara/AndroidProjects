package com.arthas.lefrango.util.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.arthas.lefrango.R

inline fun Activity.dialog(
    message: CharSequence? = null,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(this, message).apply {
        func()
    }.create()
}

inline fun Activity.dialog(
    messageResource: Int = 0,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this, message).apply {
        func()
    }.create()
}

inline fun Fragment.dialog(
    message: CharSequence? = null,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(this.requireContext(), message).apply {
        func()
    }.create()
}

inline fun Fragment.dialog(
    messageResource: Int = 0,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this.requireContext(), message).apply {
        func()
    }.create()
}

inline fun Context.dialog(
    message: CharSequence? = null,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(this, message).apply {
        func()
    }.create()
}

inline fun Context.dialog(
    messageResource: Int = 0,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    val message = if (messageResource == 0) null else getString(messageResource)
    return AlertDialogHelper(this, message).apply {
        func()
    }.create()
}

@SuppressLint("InflateParams")
class AlertDialogHelper(context: Context, message: CharSequence?) {

    private val dialogView: View by lazyFast {
        LayoutInflater.from(context).inflate(R.layout.dialog_info, null)
    }

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(dialogView)

    private val message: TextView by lazyFast {
        dialogView.findViewById<TextView>(R.id.dialogMessage)
    }

    private val positiveButton: Button by lazyFast {
        dialogView.findViewById<Button>(R.id.btnPositive)
    }

    private val negativeButton: Button by lazyFast {
        dialogView.findViewById<Button>(R.id.btnNegative)
    }

    private var dialog: AlertDialog? = null

    var cancelable: Boolean = true

    init {
        this.message.text = message
    }

    fun positiveButton(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        with(positiveButton) {
            text = builder.context.getString(textResource)
            setClickListenerToDialogButton(func)
        }
    }

    fun positiveButton(text: CharSequence, func: (() -> Unit)? = null) {
        with(positiveButton) {
            this.text = text
            setClickListenerToDialogButton(func)
        }
    }

    fun negativeButton(@StringRes textResource: Int, func: (() -> Unit)? = null) {
        with(negativeButton) {
            text = builder.context.getString(textResource)
            setClickListenerToDialogButton(func)
        }
    }

    fun negativeButton(text: CharSequence, func: (() -> Unit)? = null) {
        with(negativeButton) {
            this.text = text
            setClickListenerToDialogButton(func)
        }
    }

    fun onCancel(func: () -> Unit) {
        builder.setOnCancelListener { func() }
    }

    fun create(): AlertDialog {
        message.goneIfTextEmpty()
        positiveButton.goneIfTextEmpty()
        negativeButton.goneIfTextEmpty()

        dialog = builder
            .setCancelable(cancelable)
            .create()
        return dialog!!
    }

    private fun TextView.goneIfTextEmpty() {
        visibility = if (text.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
}

fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}