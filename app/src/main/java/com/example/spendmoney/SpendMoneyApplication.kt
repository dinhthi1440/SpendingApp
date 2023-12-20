package com.example.spendmoney

import android.app.Application
import android.util.Log
import com.example.spendmoney.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SpendMoneyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("Đã chạy tới đây", "onCreate: ", )
        startKoin {
            androidContext(this@SpendMoneyApplication)
            modules(
                databaseModule,
                repositoryModule,
                viewModelModule,
                sharedPreferencesModule,
                dataSourceModule
            )
        }
    }
}