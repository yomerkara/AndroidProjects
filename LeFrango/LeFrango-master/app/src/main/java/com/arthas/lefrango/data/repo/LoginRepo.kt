package com.arthas.lefrango.data.repo

import android.content.Context
import com.arthas.lefrango.data.model.base.BaseResponse
import com.arthas.lefrango.data.model.base.Empty
import com.arthas.lefrango.data.model.doctorlist.DoctorListResponse
import com.arthas.lefrango.data.model.login.LoginRequest
import com.arthas.lefrango.data.model.login.LoginResponse
import com.arthas.lefrango.data.model.signup.RegisterRequest
import com.arthas.lefrango.data.model.signup.RegisterResponse
import com.arthas.lefrango.data.model.signup.ValidateRequest
import com.arthas.lefrango.service.api.AppApi
import com.arthas.lefrango.service.util.AppResult
import com.arthas.lefrango.service.util.NetworkHandler.sendRequest

interface LoginRepo {
    suspend fun login(request: LoginRequest): AppResult<BaseResponse<LoginResponse>>
    suspend fun register(request: RegisterRequest): AppResult<BaseResponse<RegisterResponse>>
    suspend fun validate(request: ValidateRequest): AppResult<BaseResponse<Empty>>
    suspend fun getDoctors(city: String): AppResult<BaseResponse<DoctorListResponse>>
}

class LoginRepoImpl(
    private val context: Context,
    private val api: AppApi
) :
    LoginRepo {
    override suspend fun login(request: LoginRequest): AppResult<BaseResponse<LoginResponse>> {
        return sendRequest(context, { api.login(request) })
    }

    override suspend fun register(request: RegisterRequest): AppResult<BaseResponse<RegisterResponse>> {
        return sendRequest(context, { api.register(request) })
    }

    override suspend fun validate(request: ValidateRequest): AppResult<BaseResponse<Empty>> {
        return sendRequest(context, { api.validate(request) })
    }

    override suspend fun getDoctors(city: String): AppResult<BaseResponse<DoctorListResponse>> {
        return sendRequest(context, { api.getDoctors(city) })
    }
}