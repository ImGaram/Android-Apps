package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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