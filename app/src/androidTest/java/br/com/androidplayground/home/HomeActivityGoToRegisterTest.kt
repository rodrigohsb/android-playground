package br.com.androidplayground.home

import br.com.androidplayground.AcceptanceTest
import br.com.androidplayground.home.ui.HomeActivity
import com.github.salomonbrys.kodein.Kodein
import org.junit.Test

/**
 * @rodrigohsb
 */
class HomeActivityGoToRegisterTest : AcceptanceTest<HomeActivity>(HomeActivity::class.java) {

    @Test
    fun testWhenGoToRegister() {

        homeRobot
                .createNewOne()
                    .isSuccess()
    }

    override val testDependencies = Kodein.Module {}
}