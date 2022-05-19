package com.arthas.lefrango.ui.menu.home.chatbotadapter

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Time {

    fun timeStamp(): String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val time = simpleDateFormat.format(Date(timeStamp.time))

        return time.toString()
    }
}