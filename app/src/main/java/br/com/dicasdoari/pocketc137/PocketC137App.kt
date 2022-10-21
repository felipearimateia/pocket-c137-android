package br.com.dicasdoari.pocketc137

import android.app.Application
import br.com.dicasdoari.pocketc137.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PocketC137App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PocketC137App)
            modules(appModule)
        }
    }
}