package br.com.androidplayground.home.robots

import android.support.test.espresso.Espresso.onView

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import br.com.androidplayground.R

/**
 * @rodrigohsb
 */
class RobotContentResult {

    fun isSuccess(): Boolean{
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        return true
    }
}