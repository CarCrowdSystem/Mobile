package com.example.driver_ccs

import android.app.Application
import com.example.driver_ccs.di.DependencyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(DependencyModule.moduleApp)
        }
    }
}