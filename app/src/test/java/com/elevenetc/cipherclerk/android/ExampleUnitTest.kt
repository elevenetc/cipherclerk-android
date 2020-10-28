package com.elevenetc.cipherclerk.android

import com.elevenetc.cipherclerk.android.lock.ViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)

        runBlockingTest {
            ViewModel().state.collect {
                println(it)
            }
        }

    }
}