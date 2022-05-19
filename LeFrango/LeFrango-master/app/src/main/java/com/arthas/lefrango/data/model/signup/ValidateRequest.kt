package com.arthas.lefrango.data.model.signup

import java.io.Serializable

data class ValidateRequest(
    val key: String,
    val email: String
) : Serializable