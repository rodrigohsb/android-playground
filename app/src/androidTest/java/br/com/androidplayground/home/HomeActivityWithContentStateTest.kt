package br.com.androidplayground.home

import br.com.androidplayground.AcceptanceTest
import br.com.androidplayground.home.ui.HomeActivity
import com.github.salomonbrys.kodein.Kodein
import org.junit.Test

/**
 * @rodrigohsb
 */
class HomeActivityWithContentStateTest : AcceptanceTest<HomeActivity>(HomeActivity::class.java) {

    @Test
    fun testWhenHasContent() {
        homeRobot
                .withContactList()
                    .isSuccess()
    }

    override val testDependencies = Kodein.Module {}
}