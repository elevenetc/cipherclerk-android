package com.elevenetc.cipherclerk.android.lock

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.ViewModel
import com.elevenetc.cipherclerk.android.lock.LockViewModel.Delete
import com.elevenetc.cipherclerk.android.lock.LockViewModel.Next


class LockStateView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    val textState: TextView
    val textLock: TextView

    lateinit var userActionListener: (ViewModel.UserAction) -> Unit
    val entry = mutableListOf<Char>()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_lock_state, this)
        textState = findViewById(R.id.text_state)
        textLock = findViewById(R.id.text_lock)
        isFocusable = true
        isFocusableInTouchMode = true

        setOnClickListener {
            showKey()
        }

        addOnUnhandledKeyEventListener(object : OnUnhandledKeyEventListener {
            override fun onUnhandledKeyEvent(v: View?, event: KeyEvent): Boolean {


                if (event.action == KeyEvent.ACTION_UP) {
                    val keyCode = event.keyCode
                    if (event.isPrintingKey) {
                        userActionListener(LockViewModel.PasswordEntry(event.unicodeChar.toChar()))
                        return true
                    } else {

                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            userActionListener(Delete)
                        } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
                            userActionListener(Next)
                        }

                        return false
                    }
                } else {
                    return false
                }
            }
        })
    }

    fun handleState(state: ViewModel.ViewState) {

        val stateName = state.javaClass.simpleName


        if (state is LockViewModel.Unlocked) {
            //navigator.
        } else if (state is LockViewModel.CreatingLock) {
            textState.text = stateName + ": " + state.lock
            //editPassword.setText(state.lock.toString())
        } else if (state is LockViewModel.CreatingLockVerify) {
            textState.text = stateName + " lock: " + state.lock + " verify:" + state.verify
            //editPassword.setText(state.verify.toString())
        }
    }

    fun showKey() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        requestFocus()
        postDelayed({ imm.toggleSoftInput(SHOW_IMPLICIT, 0) }, 250)
    }
}