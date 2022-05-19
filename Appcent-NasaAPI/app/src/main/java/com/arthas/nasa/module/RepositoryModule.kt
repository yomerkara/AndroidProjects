package com.arthas.nasa.module

import com.arthas.nasa.core.SharedPref
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single { SharedPref(androidContext()) }

}