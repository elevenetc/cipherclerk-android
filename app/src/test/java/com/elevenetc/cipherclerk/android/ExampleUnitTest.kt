package com.elevenetc.cipherclerk.android

import com.elevenetc.cipherclerk.android.lock.LockViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun addition_isCorrect() = runBlockingTest {
        //assertEquals(4, 2 + 2)

        LockViewModel().state.collect {
            println(it)
        }

    }

    val scope = TestCoroutineScope(testDispatcher)

    @Test
    fun z() = runBlocking {

        flowX.collect {
            println("received:$it")
        }

        emitter.emit(1)
        println("emitted: 1")
        delay(1000)
        emitter.emit(2)
        println("emitted: 2")
        delay(1000)
        emitter.emit(3)
        println("emitted: 3")
        delay(1000)
    }

    lateinit var emitter: FlowCollector<Int>

    val flowX: Flow<Int> = flow {
        emitter = this
    }

    val s = MutableStateFlow<Int>(0)
}