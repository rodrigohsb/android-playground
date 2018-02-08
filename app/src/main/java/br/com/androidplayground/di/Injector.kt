package br.com.androidplayground.di

import android.arch.persistence.room.Room
import android.content.Context
import br.com.androidplayground.BuildConfig
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

    val dependencies = Kodein.lazy {

        bind<ClientDAO>() with singleton {
            val db: Database = instance()
            db.clientDAO()
        }

        bind<Database>() with singleton {
            Room.databaseBuilder(context, Database::class.java, "playground").build()
        }

        bind<RetrieveContacts>() with provider {
            when(BuildConfig.DEBUG){
                true -> RetrieveContactsInMemory()
                false -> RetrieveContactsFromDatabase(instance())
            }
        }

        bind<RetrieveLabels>() with provider {
            when(BuildConfig.DEBUG){
                true -> RetrieveLabelsInMemory()
                false -> RetrieveLabelsFromDatabase()
            }
        }

        bind<HomeViewModel>() with provider {
            HomeViewModel(instance())
        }

        bind<RegisterViewModel>() with provider {
            RegisterViewModel(instance())
        }
    }
}