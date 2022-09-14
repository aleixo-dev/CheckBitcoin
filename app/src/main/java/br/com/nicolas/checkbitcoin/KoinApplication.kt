package br.com.nicolas.checkbitcoin

import android.app.Application
import br.com.nicolas.checkbitcoin.di.instance
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            androidLogger(Level.DEBUG)
            modules(instance)
        }
    }
}