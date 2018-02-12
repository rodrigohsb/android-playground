package br.com.androidplayground.home.robots

import android.support.test.espresso.Espresso.onView

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import br.com.androidplayground.R
import org.hamcrest.core.AllOf.allOf
import br.com.androidplayground.register.ui.RegisterActivity

/**
 * @rodrigohsb
 */
class RobotAddNewOneResult {

    fun isSuccess(): Boolean{

        intended(allOf(hasComponent(RegisterActivity::class.java.name)))
        onView(ViewMatchers.withId(R.id.next_btn)).check(matches(isDisplayed()))
        return true
    }
}