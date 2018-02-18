package br.com.androidplayground.register.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import br.com.androidplayground.R

/**
 * @rodrigohsb
 */
class RegisterRobot {

    fun withFineLabelList(): RegisterContentResult {
        return RegisterContentResult()
    }

    fun whenCalendarButtonClicked(): RegisterDatePickerResult {
        onView(withId(R.id.calendar)).perform(click())
        return RegisterDatePickerResult()
    }

    fun withEmptyLabelList(): RegisterEmptyResult {
        return RegisterEmptyResult()
    }
}