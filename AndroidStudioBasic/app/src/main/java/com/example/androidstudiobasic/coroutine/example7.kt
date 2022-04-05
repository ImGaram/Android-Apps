package com.example.androidstudiobasic.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    // kotlin byte code 변환()

    // 예제 1
    GlobalScope.launch {
        val userData = fetchUserData()
        val userCache = cacheUserData(userData)
        updateTextView(userCache)
    }

    // 코루틴 동작 시뮬레이션(예제 1)
    println("[in] main")
    myCoroutine(MyContinuation())
    println("[out] main")
}

// 코루틴 구현
fun myCoroutine(cont: MyContinuation) { // 콜백: Continuation
    // 한 단계씩 실행
    when (cont.label) {
        0 -> {
            println("myCoroutine(), label: ${cont.label}")
            cont.label = 1
            fetchUserData2(cont)
        }
        1 -> {
            println("myCoroutine(), label: ${cont.label}")
            val userData = cont.result
            cont.label = 2
            cacheUserData2(userData, cont)
        }
        2 -> {
            println("myCoroutine(), label: ${cont.label}")
            val userCache = cont.result
            updateTextView2(userCache)  // suspend fun 이 아니라서 continuation 이 필요없다.
        }
    }
}

fun updateTextView2(user: String) {
    println("updateTextView() called")
    println("updateTextView() 작업 완료: [텍스트 뷰에 출력 $user]")
}

fun cacheUserData2(user: String, cont: MyContinuation) {
    println("cacheUserData() called")
    val result = "[캐쉬향 $user]"
    println("cacheUserData() 작업 완료: $result")
    cont.resumeWith(Result.success(result))
}

fun fetchUserData2(cont: MyContinuation) {
    println("fetchUserData() called")
    val result = "[서버에서 받은 사용자 정보]"
    println("fetchUserData() 작업 완료 $result")
    cont.resumeWith(Result.success(result))     // resumeWith = myCoroutine 이 호출되면서 다음번 레이블로 감
}

class MyContinuation(override val context: CoroutineContext =
                         EmptyCoroutineContext): Continuation<String> {

    var label = 0
    var result = ""
    override fun resumeWith(result: Result<String>) {
        this.result = result.getOrThrow()
        println("Continuation.resumeWith")
        myCoroutine(this)
    }
}

suspend fun fetchUserData() = "user_name"

suspend fun cacheUserData(user: String) = user

fun updateTextView(user: String) = user