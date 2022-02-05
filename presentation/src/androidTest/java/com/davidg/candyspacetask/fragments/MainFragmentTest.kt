package com.davidg.candyspacetask.fragments

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.davidg.candyspacetask.R
import com.davidg.candyspacetask.activities.MainActivity
import com.davidg.candyspacetask.data.di.networkingModule
import com.davidg.candyspacetask.data.di.networkingRepoModule
import com.davidg.candyspacetask.di.presentationModule
import com.davidg.candyspacetask.domain.di.domainModule
import com.davidg.candyspacetask.waitUntilVisible
import io.mockk.spyk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

@RunWith(JUnit4::class)
class MainFragmentTest {

    private val networkModules = listOf(networkingModule, networkingRepoModule)
    private val domainModules = listOf(domainModule)
    private val presentationModules = listOf(presentationModule)

    private lateinit var mainFragment: MainFragment

    @Before
    fun setUp() {
        GlobalContext.startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            androidLogger(Level.NONE)
            modules(
                networkModules + presentationModules
                        + domainModules
            )
        }
       // IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
        mainFragment = spyk(MainFragment())
    }


    @After
    fun tearDown() {
        stopKoin()
      //  IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)

    }


    @Test
    fun getData() {
        launchFragmentInKoinContainer<MainFragment>(null, R.style.Theme_CandySpace)
        mainFragment!!.activity!!.runOnUiThread {
            mainFragment?.mainViewModel.getUsers()
        }
        onView(withId(R.id.recycler_users)).perform(waitUntilVisible(7000L))
    }


    @Test
    fun search_user(){
        launchFragmentInKoinContainer<MainFragment>(null, R.style.Theme_CandySpace)
        onView(withId(R.id.search_bar)).perform(typeText("David Gormally"));
        onView(withId(R.id.searchBtn)).perform(click())
    }


    private inline fun <reified T : Fragment> launchFragmentInKoinContainer(
        fragmentArgs: Bundle? = null,
        @StyleRes themeResId: Int = R.style.Theme_CandySpace,
        crossinline action: Fragment.() -> Unit = {}
    ) {
        val startActivityIntent = Intent.makeMainActivity(
            ComponentName(
                ApplicationProvider.getApplicationContext(),
                MainActivity::class.java
            )
        )

        ActivityScenario.launch<MainActivity>(startActivityIntent).onActivity {
            mainFragment = it.supportFragmentManager.fragmentFactory.instantiate(
                Preconditions.checkNotNull(T::class.java.classLoader),
                T::class.java.name
            ) as MainFragment
            mainFragment!!.arguments = fragmentArgs
            it.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, mainFragment!!, "")
                .commitNow()

            mainFragment!!.action()
        }
    }

}