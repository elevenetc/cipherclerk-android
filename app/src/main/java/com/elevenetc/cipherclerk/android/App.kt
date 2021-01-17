package com.elevenetc.cipherclerk.android

import android.app.Application
import android.content.Context
import com.elevenetc.cipherclerk.android.common.DB
import com.elevenetc.cipherclerk.android.common.LockRepository
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import com.elevenetc.cipherclerk.android.details.DetailsViewModel
import com.elevenetc.cipherclerk.android.lock.LockViewModel
import com.elevenetc.cipherclerk.android.navigation.Navigator
import com.elevenetc.cipherclerk.android.ui.notifications.SettingsViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val navigator = Navigator(
            Navigator.Config(
                this,
                LauncherActivity::class.java,
                R.id.root
            )
        )
        val database = DB.getDatabase(this)
        val app: Context = this

        val appModule = module {
            single { app }
            single { database }
            single { database.recordDao() }
            single { RecordsRepository(get()) }
            single { navigator }
            single { LockRepository(get()) }
            factory { LockViewModel(get()) }
            factory { SettingsViewModel(get()) }
            factory { DetailsViewModel(get()) }
        }

        startKoin {
            modules(appModule)
        }
    }
}