package com.elevenetc.cipherclerk.android

import android.app.Application
import com.elevenetc.cipherclerk.android.common.DB
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import com.elevenetc.cipherclerk.android.details.DetailsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val database = DB.getDatabase(this)

        val appModule = module {
            single { database }
            single { database.recordDao() }
            single { RecordsRepository(get()) }
            factory { DetailsViewModel(get()) }
        }

        val detailsModule = module {
            single {  }
        }

        startKoin {
            modules(appModule)
        }
    }
}