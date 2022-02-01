package com.davidg.candyspacetask

import android.app.Application
import com.davidg.candyspacetask.data.di.networkingModule
import com.davidg.candyspacetask.data.di.networkingRepoModule
import com.davidg.candyspacetask.di.presentationModule
import com.davidg.candyspacetask.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

import kotlin.time.ExperimentalTime

@ExperimentalTime
class App : Application() {

    private val networkModules = listOf(networkingModule, networkingRepoModule)
    private val domainModules = listOf(domainModule)
    private val presentationModules = listOf(presentationModule)

    override fun onCreate() {
        super.onCreate()
       startKoin {
            androidContext(this@App)
            androidLogger(Level.NONE)
            modules(
                networkModules+presentationModules
                        +domainModules
            )
        }
    }
}