package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // runBlocking 안에 있는 코드가 모두 실행되기 전까지는 main Thread 가 리턴되지 않음
    runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World")
        }

        println("Hello")
        delay(2000L)
    }

    // job 기다리기
    // delay 보다 더 좋은 예시
    runBlocking {
        val job = GlobalScope.launch {
            delay(3000L)
            println("World")
        }
        val job1 = GlobalScope.launch {
            delay(3000L)
            println("World")
        }

        println("Hello")
        job.join()  // job 이 완료될 때까지 기다린다.
        job1.join() // job 이 많아지면 관리하기 까다로워진다.
    }

    // structured concurrency
    runBlocking {
        // job 이 없어도 World 가 출력된다.
        launch {
            delay(2000L)
            println("World")
        }
        launch {
            delay(2000L)
            println("World!")
        }

        println("Hello")
        // 부모 코루틴(runBlocking)이 child 코루틴(launch)들이 다 돌때까지 기다려준다.
    }
}