package br.com.androidplayground.di

import br.com.androidplayground.home.viewmodel.HomeViewModel
import br.com.androidplayground.persistence.*
import com.github.salomonbrys.kodein.*

/**
 * @rodrigohsb
 */
class Injector {

    val dependencies = Kodein.lazy {

        bind<RetrieveContacts>() with provider {
            RetrieveContactsInMemory()
        }

        bind<HomeViewModel>() with provider {
            HomeViewModel(instance())
        }
    }
}