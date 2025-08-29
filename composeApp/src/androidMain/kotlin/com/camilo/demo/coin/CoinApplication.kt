package com.camilo.demo.coin

import android.app.Application
import com.camilo.demo.coin.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class CoinApplication : Application() , KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@CoinApplication)
        }
    }
}