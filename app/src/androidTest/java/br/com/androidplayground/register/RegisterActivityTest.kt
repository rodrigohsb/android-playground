package br.com.androidplayground.register

import br.com.androidplayground.AcceptanceTest
import br.com.androidplayground.persistence.RetrieveLabels
import br.com.androidplayground.persistence.RetrieveLabelsInMemory
import br.com.androidplayground.register.robots.RegisterRobot
import br.com.androidplayground.register.ui.RegisterActivity
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import org.junit.Test

/**
 * @rodrigohsb
 */
class RegisterActivityTest : AcceptanceTest<RegisterActivity>(RegisterActivity::class.java) {


    @Test
    fun testWhenHasContent() {
        RegisterRobot()
                .withFineLabelList()
                .isSuccess()
    }

    @Test
    fun testIfDatePickerIsShown() {
        RegisterRobot()
                .whenCalendarButtonClicked()
                .isDatePickerShown()
                .isShowingTheRightDate()
    }

    override val testDependencies = Kodein.Module(allowSilentOverride = true) {
        bind<RetrieveLabels>(overrides = true) with provider {
            RetrieveLabelsInMemory()
        }
    }
}