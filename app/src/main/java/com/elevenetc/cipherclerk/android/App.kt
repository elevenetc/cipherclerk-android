package com.elevenetc.cipherclerk.android

import android.app.Application
import com.elevenetc.cipherclerk.android.common.DB
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val database = DB.getDatabase(this)

        val appModule = module {
            single { database }
            single { database.recordDao() }
        }

        startKoin {
            modules(appModule)
        }
    }
}