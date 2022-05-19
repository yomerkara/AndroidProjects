package com.arthas.lefrango.ui.menu.home.chatbotadapter

import com.arthas.lefrango.core.Constants

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.lowercase()

        return when {

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Hi!"
                    else -> "error"
                }
            }

            //How are you?
            message.contains("how are you?") -> {
                when (random) {
                    0 -> "Fine!"
                    1 -> "I'm hungry!!"
                    2 -> "Pretty good! How about you?!"
                    else -> "error"
                }
            }

            //Open Google
            message.contains("open") && message.contains("google") -> {
                Constants.SharedPref.openGoogle
            }

            //Search
            message.contains("search") -> {
                Constants.SharedPref.openSearch
            }

            //Time
            message.contains("time") && message.contains("?") -> {
                Time.timeStamp()
            }

            //Flip
            message.contains("flip") && message.contains("coin") -> {
                var random = (0..1).random()
                val result = if (random == 0) "heads" else "tails"
                "I flipped coin $result"
            }

            else -> {
                when (random) {
                    0 -> "I don't understand you!"
                    1 -> "Idk!!"
                    2 -> "Try asking me something different!"
                    else -> "error"
                }
            }
        }
    }
}