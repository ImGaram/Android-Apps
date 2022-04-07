package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.*

fun main() {
    // 코루틴 dispatchers
    // 스레드의 이름 출력
    runBlocking {
        launch {
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default: I'm working in thread ${Thread.currentThread().name}")
        }

//        launch(newSingleThreadContext("MyOwnThread")) {
//            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//        }

        // newSingleThreadContext = 새 스레드
        // use = 스레드 닫기
        newSingleThreadContext("MyOwnThread").use {
            launch(it) {
                println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
            }
        }
    }

    // 코루틴 디버깅
    runBlocking {
        val a = async {
            log("I'm computing a piece of the answer")
            6
        }

        val b = async {
            log("I'm computing another piece of the answer")
            7
        }
        log("The answer is ${a.await() * b.await()}")
    }

    // 스레드 스위칭(이동)
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                // withContext: 스레드 스위칭
                withContext(ctx2) {
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }

    // 코루틴의 context 의 job
    runBlocking {
        // job 은 코루틴의 주요 요소
        println("My job is ${coroutineContext[Job]}")   // BlockingCoroutine

        launch {
            println("My job is ${coroutineContext[Job]}")   // StandardCoroutine
        }

        async {
            println("My job is ${coroutineContext[Job]}")   // DeferredCoroutine
        }

        // 코루틴은 coroutineContext 에서 동작한다.
        coroutineContext[Job]?.isActive?: true
    }

    // 코루틴의 부모자식 관계
    runBlocking {
        val request = launch {
            GlobalScope.launch {
                println("job1: I run in GlobalScope and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }

            launch {
                delay(100)
                println("job2: I am a child of the request coroutine!")
                delay(1000)
                println("job2: I will not execute this line if my parent request!")
            }
        }

        delay(500)
        request.cancel()    // 부모가 캔슬당해 job2 의 두번째 로그가 안찍혔다.
        delay(1000)
        println("main: Who has survived r=request cancellation?")

    }

    // 부모 코루틴의 책임
    // 모든 자식 코루틴이 끝날째까지 기다린다.
    runBlocking {
        val request = launch {
            repeat(3) { i ->
                launch {
                    delay((i+1) * 2000L)
                    println("Coroutine $i is done")
                }
            }
            println("request: I'm done and I don't explicitly join my children that are still active.")
        }
        request.join()  // join 을 사용하지 않아도 부모 안에 있는 코루틴이 다 실행됨
        println("Now processing of the request is complete")
    }

    // 코루틴 element 를 합치기
    runBlocking {
        // 코루틴에 더하는 기능이 있어 + 사용 가능
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

    // 코루틴 스코프
    runBlocking {
        val activity = Activity()
        activity.doSomething()
        println("Launched Coroutine")
        delay(500L)
        println("Destroying coroutine")
        activity.destroy()  // 모든 코루틴 중단, 안하면 메모리 낭비
        delay(3000)
    }
}

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default) // 모든 코루틴 실행

    fun destroy() { // 코루틴 취소
        mainScope.cancel()
    }

    fun doSomething() {
        // 10번 코루틴 실행
        repeat(10) { i ->
            mainScope.launch {
                delay((i+1) + 200L)
                println("Coroutine $i is done.")
            }
        }
    }
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")