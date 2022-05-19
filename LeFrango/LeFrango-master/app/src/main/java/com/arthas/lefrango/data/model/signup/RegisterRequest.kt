package com.arthas.lefrango.data.model.signup

import java.io.Serializable

data class RegisterRequest(
    val tc: String,
    val name: String,
    val surName: String,
    val birthDate: String,
    val password: String
) : Serializable