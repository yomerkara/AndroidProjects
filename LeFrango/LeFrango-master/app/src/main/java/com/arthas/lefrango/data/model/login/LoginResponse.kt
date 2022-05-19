package com.arthas.lefrango.data.model.login

import com.arthas.lefrango.data.model.signup.AuthenticatedUser
import java.io.Serializable

data class LoginResponse(
    val token: String,
    val user: AuthenticatedUser,
    val isVictim: Boolean
) : Serializable

