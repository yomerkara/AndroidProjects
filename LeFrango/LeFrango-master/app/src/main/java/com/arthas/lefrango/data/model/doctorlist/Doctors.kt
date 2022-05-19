package com.arthas.lefrango.data.model.doctorlist

import java.io.Serializable

data class Doctors(
    val name: String,
    val surname: String,
    val city: String
) : Serializable
