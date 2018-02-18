package br.com.androidplayground.register.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.PickerActions
import android.support.test.espresso.matcher.ViewMatchers.*
import br.com.androidplayground.R

/**
 * @rodrigohsb
 */
class RegisterDatePickerResult {

    fun isDatePickerShown(): RegisterDatePickerResult{

        onView(withId(R.integer.date_picker_id)).check(matches(isDisplayed()))

        return this
    }

    fun isShowingTheRightDate(): Boolean {

        onView(withId(R.integer.date_picker_id))
                .perform(PickerActions.setDate(2018, 2, 18));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.date)).check(matches(withText("18/02/2018")))

        return true
    }




}