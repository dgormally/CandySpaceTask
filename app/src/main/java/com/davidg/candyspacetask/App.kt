package com.davidg.candyspacetask

import android.app.Application
import com.davidg.candyspacetask.data.di.networkingModule
import com.davidg.candyspacetask.data.di.networkingRepoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

import kotlin.time.ExperimentalTime

@ExperimentalTime
class App : Application() {

    private val networkModules = listOf(networkingModule, networkingRepoModule)

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            androidLogger(Level.NONE)
            modules(
                networkModules

            )
        }
    }
}