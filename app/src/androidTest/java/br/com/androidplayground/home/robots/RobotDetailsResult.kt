package br.com.androidplayground.home.robots

import android.support.test.espresso.Espresso.onView

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.core.AllOf.allOf
import br.com.androidplayground.details.DetailsActivity

/**
 * @rodrigohsb
 */
class RobotDetailsResult {

    fun isSuccess(): Boolean{

        intended(allOf(hasComponent(DetailsActivity::class.java.name)))
        onView(withText("RHD")).check(matches(isDisplayed()))
        return true
    }
}