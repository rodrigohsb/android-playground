package br.com.androidplayground.register.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import br.com.androidplayground.R
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Matchers.not

/**
 * @rodrigohsb
 */
class RegisterContentResult {

    fun isSuccess(): Boolean{

        onView(withId(R.id.first_input_layout))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.second_input_layout))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.third_input_layout))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.fourth_input_layout))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.fifth_input_layout))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.textView))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        onView(withId(R.id.textView2))
                .check(matches(not(withHint(any(String::class.java)))))
                .check(matches(isDisplayed()))

        return true
    }
}