package br.com.androidplayground.home

import br.com.androidplayground.AcceptanceTest
import br.com.androidplayground.home.ui.HomeActivity
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.RetrieveContactsInMemory
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
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

    override val testDependencies = Kodein.Module(allowSilentOverride = true) {
        bind<RetrieveContacts>(overrides = true) with provider {
            RetrieveContactsInMemory()
        }
    }
}