package com.arthas.lefrango.data.model.signup

import java.io.Serializable

data class AuthenticatedUser(
    val id:Int,
    val tc: String,
    val name: String,
    val surName: String,
    val birthDate: String,
    val password: String,
    val salt:String,
    val isBanned : Boolean?
) : Serializable
