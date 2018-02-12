package br.com.androidplayground.home.robots

import android.support.test.espresso.Espresso.onView

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import br.com.androidplayground.R

/**
 * @rodrigohsb
 */
class RobotEmptyResult {

    fun isSuccess(): Boolean{

        onView(ViewMatchers.withId(R.id.homeEmptyView)).check(matches(isDisplayed()))
        return true
    }
}