package com.davidg.candyspacetask.activities

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.davidg.candyspacetask.data.di.networkingModule
import com.davidg.candyspacetask.data.di.networkingRepoModule
import com.davidg.candyspacetask.di.presentationModule
import com.davidg.candyspacetask.domain.di.domainModule
import org.junit.After

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest: KoinTest{


    private val networkModules = listOf(networkingModule, networkingRepoModule)
    private val domainModules = listOf(domainModule)
    private val presentationModules = listOf(presentationModule)

    @get:Rule
    val mActivityRule =  ActivityTestRule(MainActivity::class.java, true, false)


    @Before
    fun setUp(){
        GlobalContext.startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            androidLogger(Level.NONE)
            modules(
                networkModules + presentationModules
                        + domainModules
            )
        }
        //IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }


    @After
    fun tearDown(){
        stopKoin()
/*
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
*/
    }

    @Test
    fun test_launch_main_activity(){
        mActivityRule.launchActivity(Intent())
    }
}