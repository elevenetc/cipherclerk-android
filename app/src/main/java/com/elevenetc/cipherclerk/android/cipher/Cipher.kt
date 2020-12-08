package com.elevenetc.cipherclerk.android.cipher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class Cipher(
    private val key: String,
    private val context: CoroutineContext
) : CoroutineScope {

    suspend fun encode(value: String): String = withContext(context) {
        value + key
    }

    suspend fun decode(value: String): String = withContext(context) {
        value.substring(0, key.length - 1)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}