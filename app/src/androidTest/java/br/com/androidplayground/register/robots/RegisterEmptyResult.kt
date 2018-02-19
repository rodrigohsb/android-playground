package br.com.androidplayground.register.robots

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import br.com.androidplayground.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * @rodrigohsb
 */
class RegisterEmptyResult {

    fun isSuccess(): Boolean{

        onView(withId(R.id.first_input_layout))
                .check(matches(withHint("")))

        onView(withId(R.id.second_input_layout))
                .check(matches(withHint("")))

        onView(withId(R.id.third_input_layout))
                .check(matches(withHint("")))

        onView(withId(R.id.fourth_input_layout))
                .check(matches(withHint("")))

        onView(withId(R.id.fifth_input_layout))
                .check(matches(withHint("")))

        onView(withId(R.id.textView))
                .check(matches(withText("")))

        onView(withId(R.id.textView2))
                .check(matches(withText("")))
        return true
    }

    private fun withHint(expectedHint: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            override fun matchesSafely(item: View?): Boolean {

                if (item !is TextInputLayout) return false

                val hint = item.hint?: ""
                return expectedHint == hint
            }
            override fun describeTo(description: Description?) {}

        }
    }

}