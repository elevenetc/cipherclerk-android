package com.elevenetc.cipherclerk.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elevenetc.cipherclerk.android.home.MainFragment
import com.elevenetc.cipherclerk.android.navigation.Navigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.addRootScreen(MainFragment.create())
        }
    }
}