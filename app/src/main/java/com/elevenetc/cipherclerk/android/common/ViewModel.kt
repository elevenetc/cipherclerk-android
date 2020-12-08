package com.elevenetc.cipherclerk.android.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

open class ViewModel : CoroutineScope {

    lateinit var fc: FlowCollector<ViewState>

    val state: Flow<ViewState> = flow {
        fc = this
    }

    open fun onUserAction(action: UserAction) {

    }

    open class UserAction
    open class ViewState

    object Loading : ViewState()
    data class Error(val t: Throwable) : ViewState()
    data class Result<T>(val result: T) : ViewState()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}