package com.arthas.lefrango.data.model.signup

import java.io.Serializable

data class RegisterResponse(
    val token: String,
    val authUser: AuthenticatedUser,
    val tcVerified : Boolean,
    val status: Boolean
) : Serializable
