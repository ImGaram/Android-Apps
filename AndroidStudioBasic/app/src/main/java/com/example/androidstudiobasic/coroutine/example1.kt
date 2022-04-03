package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() {
    // launch: 코루틴 빌더
    // launch 는 코루틴 스코프 안에서 샐행됨
    GlobalScope.launch {
        // launch 안에서 코루틴 실행
        delay(1000L)
        println("World")    // 2
    }
    println("Hello")    // 1
    Thread.sleep(2000L)

    // GlobalScope.launch 랑 같은 역할
    thread {
        Thread.sleep(1000L)
        println("World2")
    }
    println("Hello2")
    Thread.sleep(2000L)

    // runBlocking = launch
    println("Hello")
    runBlocking {
        delay(1000L)    // suspend fun 인 delay 를 사용할수 있다.
        // launch 는 자신을 호출하는 스레드를 blocking 하지 않는다
        // runBlocking 은 그 반대
    }
}