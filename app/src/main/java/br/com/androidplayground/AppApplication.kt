package br.com.androidplayground

import android.app.Application
import br.com.androidplayground.di.Injector
import com.github.salomonbrys.kodein.KodeinAware

/**
 * @rodrigohsb
 */
class AppApplication : Application(), KodeinAware {

    companion object {
        lateinit var instance: AppApplication
    }

    override val kodein by Injector().dependencies

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}