package com.demetrius.testevents

import android.app.Application
import com.demetrius.testevents.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestEventsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestEventsApplication)
            modules(appModules)
        }
    }
}