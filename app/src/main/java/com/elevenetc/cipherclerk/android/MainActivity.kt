package com.elevenetc.cipherclerk.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elevenetc.cipherclerk.android.home.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.root, MainFragment.create())
                .commit()
    }
}