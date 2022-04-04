package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    // 타임아웃
    runBlocking {
        // 코루틴 실행할때 사간이 지나면 코루틴이 취소됨
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i")
                delay(500L)
            }
        }
    }   // 예외가 발생함

    // 해결 방법
    runBlocking {
        // withTimeoutOrNull: 타임아웃 할때 null 인지 아닌지 확인
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i")
                delay(500L)
            }
            "Done"
        }
        println("Result is $result")
    }
}