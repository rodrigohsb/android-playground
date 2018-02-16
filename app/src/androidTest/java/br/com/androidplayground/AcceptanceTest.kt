package br.com.androidplayground

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.Intents
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.salomonbrys.kodein.Kodein
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>){

    @Rule
    @JvmField
    val testRule: ActivityTestRule<T> = ActivityTestRule(clazz, true, false)

    @Before
    fun setup() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.asApp()
        app.resetInjection()
        app.addModule(testDependencies)
        Intents.init()

        startActivity()
    }

    private fun startActivity(args: Bundle = Bundle()): T {
        val intent = Intent()
        intent.putExtras(args)
        return testRule.launchActivity(intent)
    }

    abstract val testDependencies: Kodein.Module

    @After
    fun tearDown(){
        Intents.release()
    }
}