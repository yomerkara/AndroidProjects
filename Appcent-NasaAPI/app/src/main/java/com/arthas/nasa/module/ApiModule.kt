package com.arthas.nasa.module

import com.arthas.nasa.service.api.AppApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideAppApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }
    single { provideAppApi(get()) }

}