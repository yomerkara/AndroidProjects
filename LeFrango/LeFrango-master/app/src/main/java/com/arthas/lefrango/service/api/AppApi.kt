package com.arthas.lefrango.service.api

import com.arthas.lefrango.data.model.base.BaseResponse
import com.arthas.lefrango.data.model.base.Empty
import com.arthas.lefrango.data.model.doctorlist.DoctorListResponse
import com.arthas.lefrango.data.model.login.LoginRequest
import com.arthas.lefrango.data.model.login.LoginResponse
import com.arthas.lefrango.data.model.signup.RegisterRequest
import com.arthas.lefrango.data.model.signup.RegisterResponse
import com.arthas.lefrango.data.model.signup.ValidateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AppApi {

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<BaseResponse<RegisterResponse>>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<BaseResponse<LoginResponse>>

    @POST("Account/Validate")
    suspend fun validate(@Body request: ValidateRequest): Response<BaseResponse<Empty>>

    @GET("operation/doctors")
    suspend fun getDoctors(@Query("city")city: String): Response<BaseResponse<DoctorListResponse>>

}