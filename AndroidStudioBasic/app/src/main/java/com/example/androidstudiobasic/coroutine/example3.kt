package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() {
    // suspend fun 사용해 보기
    runBlocking {
        launch {
            myWorld()
        }
        println("Hello")
    }

    runBlocking {
        // 코루틴의 얼마나 가벼운지 보여주는 예제
        repeat(100_000) {
            launch {
                delay(1000L)
                print(".")
            }

            // thread 를 사용하는 방법은 코루틴을 쓸때보다 더 버벅거린다
            thread {
                Thread.sleep(100L)
                println(".")
            }
        }
    }

    runBlocking {
        // 코루틴이 실행되고 있다고 해도 프로세스가 유지되는 것은 아니다는 것을 보여주는 예제
        GlobalScope.launch {
            repeat(1000) { i ->
                // main 이 끝나면 repeat 도 같이 끝난다.
                println("I'm sleeping $i ... ")
                delay(500L)
            }
        }
        delay(1300L)
    }

    // 일시중단과 재개
    runBlocking {
        launch {
            repeat(5) { i ->
                println("Coroutine A: $i")
                delay(10L)  // delay 룰 거치면 코루틴이 잠시 중단된다.
            }

            repeat(5) { i ->
                println("Coroutine B: $i")
            }
        }

        println("Coroutine Outer")
    }
}

// suspend fun 은 suspend fun 안이나 코루틴 안에서 호출이 가능하다.
suspend fun myWorld() {
    delay(1000L)
    println("World")
}