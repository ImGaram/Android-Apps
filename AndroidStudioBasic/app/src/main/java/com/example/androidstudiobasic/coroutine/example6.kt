package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    // suspend fun 구성하기
    runBlocking {
        val time = measureTimeMillis {
            // 일반 코드처럼 작성해도 순차적으로 흐름
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("completed in $time ms")
    }

    // async 사용하기
    runBlocking {
        val time = measureTimeMillis {
            // 일반 코드처럼 작성해도 순차적으로 흐름
            val one = async { doSomethingUsefulOne() }  // 실행되고 바로 돌아옴
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("completed in $time ms")
    }

    // async = 코루틴 빌더
    // 늦은 async 스타트
    runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() } // 나중에 실행된다.
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }

            one.start() // 없으면 2초가 걸림
            two.start()
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    // 안되는 경우(async-style 함수)
    val time = measureTimeMillis {
        val one = somethingUsefulOneAsync()     // 예외가 발생한 경우 돌이킬수 없는 상황 발생
        val two = somethingUsefulTwoAsync()     // 일반함수(어디서든 사용할 수 있다.)
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")

    // structured concurrency async(async-style 해결 방법)
    runBlocking {
        // 예외 발생시 모든 코루틴이 취소됨 => 더 안전함
        val time = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }
        println("Completed in $time ms")
    }

    // 취소된 코루틴의 전파
    runBlocking<Unit> {
        try {
            failedConcurrentSum()
        } catch (e:ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE)
            42
        } finally {
            println("First child was canceled")
        }
    }

    val two = async<Int> {
        println("Second child throws an Exception")
        throw ArithmeticException()     // 예외 발생시 모든 코루틴에세 전파
    }

    one.await() + two.await()
}

// 잘못된 함수
fun somethingUsefulOneAsync() = GlobalScope.async { doSomethingUsefulOne() }
fun somethingUsefulTwoAsync() = GlobalScope.async { doSomethingUsefulOne() }

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }

//    delay(10L)
//    println("Exception")
//    throw Exception()

    one.await() + two.await()
}

// 호출하면 코루틴이 일시중단됨
suspend fun doSomethingUsefulOne(): Int {
    // 유용한 동작을 하는 곳(서버 호출, 무거운 계산)
    println("doSomethingUsefulOne")
    delay(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L)
    return 29
}