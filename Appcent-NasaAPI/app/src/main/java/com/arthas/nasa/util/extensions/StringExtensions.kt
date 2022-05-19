package com.arthas.nasa.util.extensions

import com.arthas.nasa.util.RegexUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    val expression = RegexUtils.email.pattern
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.formatDate(parserFormat: String, formatterFormat: String): String {
    if (this.isBlank().not()) {
        val parser = SimpleDateFormat(parserFormat, Locale.getDefault())
        val formatter = SimpleDateFormat(formatterFormat, Locale.getDefault())
        val date = parser.parse(this)
        date?.let {
            return formatter.format(date)
        }
    }
    return ""
}

fun String.isTCKNCorrect(): Boolean {
    if (this.length != 11) return false
    val chars = this.toCharArray()
    val a = IntArray(11)
    for (i in 0..10) {
        a[i] = chars[i] - '0'
    }
    if (a[0] == 0) return false
    if (a[10] % 2 == 1) return false
    if (((a[0] + a[2] + a[4] + a[6] + a[8]) * 7 - (a[1] + a[3] + a[5] + a[7])) % 10 != a[9]) return false
    return (a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9]) % 10 == a[10]
}

fun String.toToken(): String {
    return "Bearer $this"
}
