package com.elevenetc.cipherclerk.android.common

import android.content.Context
import com.elevenetc.cipherclerk.android.common.LockRepository.State.Locked
import com.elevenetc.cipherclerk.android.common.LockRepository.State.Unlocked

class LockRepository(val context: Context) {

    var state: State = Locked
    private val password = mutableListOf<Char>()

    fun hasPassword(): Boolean {
        return password.isNotEmpty()
    }

    fun createAndUnlock(pass: List<Char>) {
        if (state == Locked && password.isEmpty()) {
            password.clear()
            password.addAll(pass)
            state = Unlocked
        }
    }

    fun lock() {
        if (state is Unlocked && hasPassword()) {
            state = Locked
        }
    }

    fun lock(pass: List<Char>) {
        if (state is Unlocked && !hasPassword()) {
            password.addAll(pass)
            state = Locked
        }
    }

    fun unlock(pass: List<Char>) {
        if (state == Locked && hasPassword()) {
            if (pass == password) {
                state = Unlocked
            }
        }
    }

    sealed class State {
        object Locked : State()
        object Unlocked : State()
    }
}