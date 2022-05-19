package com.arthas.nasa.core

import android.app.Application
import com.arthas.nasa.module.apiModule
import com.arthas.nasa.module.networkModule
import com.arthas.nasa.module.repositoryModule
import com.arthas.nasa.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NasaCaseStudy : Application() {

    private val modules = arrayOf(apiModule, networkModule, repositoryModule, viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NasaCaseStudy)
            koin.loadModules(
                modules.toList()
            )
        }
    }

}