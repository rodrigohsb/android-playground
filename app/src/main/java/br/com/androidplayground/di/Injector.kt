package br.com.androidplayground.di

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import android.arch.persistence.room.Room
import br.com.androidplayground.home.handler.ContactsHandler
import br.com.androidplayground.home.viewmodel.HomeViewModel
import br.com.androidplayground.persistence.*
import br.com.androidplayground.persistence.dao.ClientDAO
import br.com.androidplayground.register.handler.ContactHandler
import br.com.androidplayground.register.validator.*
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
            RetrieveLabelsFromFile()
        }

        bind<HomeViewModel>() with provider {
            HomeViewModel(instance(), instance())
        }

        bind<ContactsHandler>() with provider {
            ContactsHandler()
        }

        bind<RegisterViewModel>() with provider {
            RegisterViewModel(instance(),instance(),instance(),instance())
        }

        bind<CNPJValidator>() with provider {
            CNPJValidator()
        }
        bind<DateValidator>() with provider {
            DateValidator()
        }
        bind<EmailValidator>() with provider {
            EmailValidator()
        }
        bind<FantasyNameValidator>() with provider {
            FantasyNameValidator()
        }
        bind<NameValidator>() with provider {
            NameValidator()
        }
        bind<PhoneValidator>() with provider {
            PhoneValidator()
        }
        bind<ContactHandler>() with provider {
            ContactHandler()
        }
        bind<Validator>() with provider {
            Validator(instance(),instance(),instance(),instance(),instance(),instance())
        }
    }
}