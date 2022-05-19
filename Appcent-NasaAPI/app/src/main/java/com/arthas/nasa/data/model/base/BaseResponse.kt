package com.arthas.nasa.data.model.base

import java.io.Serializable

data class BaseResponse<T>(
    val message: String,
    val data: T,
    val httpStatusCode: Int,
    val success: Boolean
) : Serializable
