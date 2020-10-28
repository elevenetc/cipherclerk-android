package com.elevenetc.cipherclerk.android.lock

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.elevenetc.cipherclerk.android.R

class LockKeyboard(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.view_lock_keyboard, this)

        val vm = ViewModel()

        findViewById<View>(R.id.btn_0).setOnClickListener { vm.addKey(0) }
        findViewById<View>(R.id.btn_1).setOnClickListener { vm.addKey(1) }
        findViewById<View>(R.id.btn_2).setOnClickListener { vm.addKey(2) }
        findViewById<View>(R.id.btn_3).setOnClickListener { vm.addKey(3) }
        findViewById<View>(R.id.btn_4).setOnClickListener { vm.addKey(4) }
        findViewById<View>(R.id.btn_5).setOnClickListener { vm.addKey(5) }
        findViewById<View>(R.id.btn_6).setOnClickListener { vm.addKey(6) }
        findViewById<View>(R.id.btn_7).setOnClickListener { vm.addKey(7) }
        findViewById<View>(R.id.btn_8).setOnClickListener { vm.addKey(8) }
        findViewById<View>(R.id.btn_9).setOnClickListener { vm.addKey(9) }
    }
}