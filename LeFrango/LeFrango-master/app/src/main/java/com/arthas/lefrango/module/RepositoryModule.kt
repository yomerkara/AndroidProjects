package com.arthas.lefrango.module

import android.content.Context
import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.data.repo.LoginRepo
import com.arthas.lefrango.data.repo.LoginRepoImpl
import com.arthas.lefrango.service.api.AppApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single { SharedPref(androidContext()) }

    fun provideLoginRepository(
        context: Context,
        api: AppApi
    ): LoginRepo {
        return LoginRepoImpl(context, api)
    }
    single { provideLoginRepository(androidContext(), get()) }

}