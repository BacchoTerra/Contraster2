package com.brunoterra.contraster2

import android.app.Application
import com.brunoterra.contraster2.domain.di.domainModule
import com.brunoterra.contraster2.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(domainModule, presentationModule)
        }
    }
}
