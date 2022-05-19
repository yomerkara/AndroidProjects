package com.arthas.lefrango.data.model.doctorlist

import java.io.Serializable

data class DoctorListResponse(
    val doctor: List<Doctors>
) : Serializable
