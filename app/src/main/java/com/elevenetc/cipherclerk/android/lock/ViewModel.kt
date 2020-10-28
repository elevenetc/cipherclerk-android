package com.elevenetc.cipherclerk.android.lock

import com.elevenetc.cipherclerk.android.lock.ViewModel.State.Entry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class ViewModel {

    lateinit var flowCollector:FlowCollector<State>

    val state: Flow<State> = flow {
        flowCollector = this
        emit(Entry)
        Thread.sleep(1000)
        emit(Entry)
    }

    fun addKey(value: Int) {
        flowCollector.emit(State.Verifying)
    }

    sealed class State {
        object Entry : State()
        object Verifying : State()
        object VerifiedSuccess : State()
        object VerifiedFailed : State()
    }
}