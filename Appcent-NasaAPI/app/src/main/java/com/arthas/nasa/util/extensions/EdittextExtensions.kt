package com.arthas.nasa.util.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.arthas.nasa.R
import java.util.*

fun EditText.addDateFormatterListener() {
    var current = ""
    val dateFormat = this.context.resources.getString(R.string.description1)
    val cal = Calendar.getInstance()
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString() != current) {
                var clean: String = s.toString().replace(("[^\\d.]|\\.").toRegex(), "")
                val cleanC: String = current.replace(("[^\\d.]|\\.").toRegex(), "")
                val cl = clean.length
                var sel = cl
                var i = 2
                while (i <= cl && i < 6) {
                    sel++
                    i += 2
                }
                if (clean == cleanC) sel--
                if (clean.length < 8) {
                    clean += dateFormat.substring(clean.length)
                } else {
                    var day = clean.substring(0, 2).toInt()
                    var mon = clean.substring(2, 4).toInt()
                    var year = clean.substring(4, 8).toInt()
                    mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
                    cal.set(Calendar.MONTH, mon - 1)
                    year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                    cal.set(Calendar.YEAR, year)
                    day =
                        if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(Calendar.DATE) else day
                    clean = String.format("%02d%02d%02d", day, mon, year)
                }
                clean = String.format(
                    "%s/%s/%s", clean.substring(0, 2),
                    clean.substring(2, 4),
                    clean.substring(4, 8)
                )
                sel = if (sel < 0) 0 else sel
                current = clean
                this@addDateFormatterListener.setText(current)
                this@addDateFormatterListener.setSelection(if (sel < current.length) sel else current.length)
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    })
}