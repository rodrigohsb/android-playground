package br.com.androidplayground.home

import br.com.androidplayground.AcceptanceTest
import br.com.androidplayground.home.ui.HomeActivity
import org.junit.Test
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.RetrieveEmptyContacts
import com.github.salomonbrys.kodein.*

/**
 * @rodrigohsb
 */
class HomeActivityEmptyStateTest : AcceptanceTest<HomeActivity>(HomeActivity::class.java) {

    @Test
    fun testWhenHasNoContent() {
        homeRobot
                .withoutContacts()
                    .isSuccess()
    }

    override val testDependencies = Kodein.Module(allowSilentOverride = true) {
        bind<RetrieveContacts>(overrides = true) with provider {
            RetrieveEmptyContacts()
        }
    }

}