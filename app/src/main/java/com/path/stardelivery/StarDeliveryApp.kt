package com.path.stardelivery

import android.app.Application
import com.path.stardelivery.di.dataModule
import com.path.stardelivery.di.networkModule
import com.path.stardelivery.di.repositoryModule
import com.path.stardelivery.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StarDeliveryApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StarDeliveryApp)
            androidLogger(Level.ERROR)
            modules(networkModule + dataModule + repositoryModule + viewModelModule)
        }
    }
}