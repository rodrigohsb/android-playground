package br.com.androidplayground.register.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.PickerActions
import android.support.test.espresso.matcher.ViewMatchers.*
import br.com.androidplayground.R
import kotlinx.android.synthetic.main.layout_register_user.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @rodrigohsb
 */
class RegisterDatePickerResult {

    fun isDatePickerShown(): RegisterDatePickerResult{

        onView(withId(R.integer.date_picker_id)).check(matches(isDisplayed()))

        return this
    }

    fun isShowingTheRightDate(): Boolean {

        val now = Calendar.getInstance()

        val year = now.get(Calendar.YEAR)
        val month = now.get(Calendar.MONTH)+1
        val day = now.get(Calendar.DAY_OF_MONTH)

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = sdf.format(now.time)

        onView(withId(R.integer.date_picker_id))
                .perform(PickerActions.setDate(year, month, day))

        onView(withText("OK")).perform(click())

        Thread.sleep(4000)

        onView(withId(R.id.date)).check(matches(withText("19/02/2018")))

        return true
    }




}