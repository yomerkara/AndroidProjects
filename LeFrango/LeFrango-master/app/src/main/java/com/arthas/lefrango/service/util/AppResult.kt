package com.arthas.lefrango.service.util

sealed class AppResult<out T> {
    data class Success<out T>(val successData: T) : AppResult<T>()
    class Error(
        private val exception: java.lang.Exception,
        val message: String = exception.localizedMessage ?: ""
    ) : AppResult<Nothing>()
}