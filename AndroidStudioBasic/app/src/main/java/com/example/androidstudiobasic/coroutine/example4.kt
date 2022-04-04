package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.*
import java.lang.Exception

fun main() {
    // 코루틴 취소
    runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("job: I'm sleeping $i")
                delay(500L)
            }
        }

        delay(1300L)
        println("main: I:'m tried to waiting!")
        job.cancel()    // 코루틴 취소 코드
        job.join()
        println("main: Now I can quit")
    }

    // 코루틴의 취소 조건(협조적 코드 필요)
    // 1. suspend fun 사용
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {

            try {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) {
                    if (System.currentTimeMillis() >= nextPrintTime) {
//                    delay(1L)
                        yield()     // delay 와 같은 역할(코루틴이 재개될때 예외를 던짐) = 강제 종료
                        println("job: I'm sleeping ${i++}")
                        nextPrintTime += 500L
                    }
                }
            } catch (e: Exception) {
                println("Exception [$e]")
            }
        }
        delay(1300L)
        println("main: I'm tried of waiting!")
        job.cancelAndJoin()     // cancel 과 join 을 순서대로 불러줌
        println("main: Now I can quit.")
        // suspend fun 은 취소가 가능함(캔슬되는지 확인 가능)
    }

    // 2. 명시적 상대 체크(isActive 활용)
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0

            println("isActive: $isActive")
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++}")
                    nextPrintTime += 500L
                }
            }
            println("isActive: $isActive")
        }
        delay(1300L)
        println("main: I'm tried of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }

    // 코루틴 종료시 리소스 혜제
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                     println("job: I'm sleeping $i")
                    delay(500L)
                }
            } finally {
                // 리소스 혜제 구역 마지막에 출력
                println("job: I'm running finally")
            }
        }
        delay(1300L)
        println("main: I'm tried of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }

    // 취소된 코루틴에 코루틴 부르기(특수 상황)
    runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i")
                    delay(500L)
                }
            } finally { // finally 안에서 다시 코루틴이 동작
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because")
                }
            }
        }
        delay(1300L)
        println("main: I'm tried of waiting!")
        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}