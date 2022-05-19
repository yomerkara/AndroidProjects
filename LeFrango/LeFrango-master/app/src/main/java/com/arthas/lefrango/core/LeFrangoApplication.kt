package com.arthas.lefrango.core

import android.app.Application
import com.arthas.lefrango.module.apiModule
import com.arthas.lefrango.module.networkModule
import com.arthas.lefrango.module.repositoryModule
import com.arthas.lefrango.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LeFrangoApplication : Application() {

    private val modules = arrayOf(apiModule, networkModule, repositoryModule, viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LeFrangoApplication)
            koin.loadModules(
                modules.toList()
            )
        }
    }

}