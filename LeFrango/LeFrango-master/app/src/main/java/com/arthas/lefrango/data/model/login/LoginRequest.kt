package com.arthas.lefrango.data.model.login

import java.io.Serializable

data class LoginRequest(
    val tc: String,
    val password: String
) : Serializable