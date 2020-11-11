package com.elevenetc.cipherclerk.android.lock

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elevenetc.cipherclerk.android.MainActivity
import com.elevenetc.cipherclerk.android.R

class LockActivity : AppCompatActivity(R.layout.activity_lock) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
    }
}