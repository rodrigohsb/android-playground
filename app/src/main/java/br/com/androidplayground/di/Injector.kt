package br.com.androidplayground.di

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import android.arch.persistence.room.Room
import br.com.androidplayground.BuildConfig
import br.com.androidplayground.home.handler.ContactsHandler
import br.com.androidplayground.home.viewmodel.HomeViewModel
import br.com.androidplayground.persistence.*
import br.com.androidplayground.persistence.dao.ClientDAO
import br.com.androidplayground.persistence.RetrieveLabelsFromDatabase
import br.com.androidplayground.register.viewmodel.RegisterViewModel
import com.github.salomonbrys.kodein.*

/**
 * @rodrigohsb
 */

class Injector(private val context: Context) {

    val dependencies = Kodein.Module(allowSilentOverride = true) {

        bind<ClientDAO>() with singleton {
            val db: Database = instance()
            db.clientDAO()
        }

        bind<Database>() with singleton {
            Room.databaseBuilder(context, Database::class.java, "playground")
                    .allowMainThreadQueries()
                    .build()
        }

        bind<RetrieveContacts>() with provider {
            RetrieveContactsFromDatabase(instance())
        }

        bind<RetrieveLabels>() with provider {
            when (BuildConfig.DEBUG) {
                true -> RetrieveLabelsInMemory()
                false -> RetrieveLabelsFromDatabase()
            }
        }

        bind<HomeViewModel>() with provider {
            HomeViewModel(instance(), instance())
        }

        bind<ContactsHandler>() with provider {
            ContactsHandler()
        }

        bind<RegisterViewModel>() with provider {
            RegisterViewModel(instance())
        }
    }
}