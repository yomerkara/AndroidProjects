package com.arthas.nasa.data.model.photos

import java.io.Serializable

data class Camera(
    val id: Int,
    val name: String,
    val rover_id: Int,
    val full_name: String
) : Serializable
