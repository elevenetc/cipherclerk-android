package com.elevenetc.cipherclerk.android

import com.elevenetc.cipherclerk.android.cipher.Cipher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class CipherTests {

    val td = TestCoroutineDispatcher()

    @Test
    fun test() {
        val cipher = Cipher("hello", td)

        runBlockingTest {
            val initValue = "a"
            val encoded = cipher.encode(initValue)
            val decoded = cipher.decode(encoded)

            println(encoded)
            println(decoded)
        }



    }
}