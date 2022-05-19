package com.arthas.nasa.data.model.photos

import java.io.Serializable

data class Rover(
    val id: String,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String
) : Serializable
