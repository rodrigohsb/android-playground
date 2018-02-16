package br.com.androidplayground.home.robots

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import br.com.androidplayground.R
import br.com.androidplayground.home.adapter.HomeAdapter

/**
 * @rodrigohsb
 */

class HomeRobot {

    fun withContactList(): RobotContentResult {
        return RobotContentResult()
    }

    fun withoutContacts(): RobotEmptyResult {
        return RobotEmptyResult()
    }

    fun seeDetails(): RobotDetailsResult {

        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()))

        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.ViewHolder>(0, ViewActions.click()))

        return RobotDetailsResult()
    }

    fun createNewOne(): RobotAddNewOneResult {
        Espresso.onView(ViewMatchers.withId(R.id.homeAddBtn)).perform(ViewActions.click())
        return RobotAddNewOneResult()
    }
}