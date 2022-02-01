package com.davidg.candyspacetask.fragments
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.activities.MainActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.*
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainFragmentTest : AutoCloseKoinTest() {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    /**
     * Recyclerview comes into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {

    }

}