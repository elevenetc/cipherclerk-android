package com.elevenetc.cipherclerk.android.lock

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.elevenetc.cipherclerk.android.R

class LockKeyboard(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.view_lock_keyboard, this)
    }
}