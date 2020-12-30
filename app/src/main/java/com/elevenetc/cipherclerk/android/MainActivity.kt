package com.elevenetc.cipherclerk.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.elevenetc.cipherclerk.android.home.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        lateinit var fm: FragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fm = supportFragmentManager
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.root, MainFragment.create())
                .commit()
    }
}