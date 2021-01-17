package com.elevenetc.cipherclerk.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elevenetc.cipherclerk.android.common.LockRepository
import com.elevenetc.cipherclerk.android.common.LockRepository.State.Locked
import com.elevenetc.cipherclerk.android.home.HomeFragment
import com.elevenetc.cipherclerk.android.lock.LockFragment
import com.elevenetc.cipherclerk.android.navigation.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class LauncherActivity : AppCompatActivity(R.layout.activity_main), CoroutineScope {

    val navigator: Navigator by inject()
    val lockRepository: LockRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

            launch {
                if (lockRepository.state == Locked) {
                    navigator.addRootScreen(LockFragment(), false)
                } else {
                    navigator.addRootScreen(HomeFragment.create(), false)
                }
            }

        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}