package com.example.androidkotlinbook.grammer

// null safety
// 널 안정성 : 널 포인트 예외가 발생하지 않도록 코드를 작성하는 것

// !! 연산자
fun some(data:String?):Int {
    return data!!.length
}

fun main() {
    // 객체에 널 대입
    val data1: String = "hello"
    val data2: String? = null

    // 널 안정성을 개발자가 고려한 코드
    var data3: String? = null
    val length = if (data3 == null){
        0
    }else {
        data3.length
    }
    println("data.length : $length")
    println()

    // 널 안정성 연산자를 이용한 코드
    var data4: String? = null
    println("data.length : ${data4?.length?:0}")
    println()

    // ? : 널 허용
    var data5:String = "im"
//    data5 = null    // 오류!

    var data6:String? = "im"
    data6 = null    // ?로 인해 널 허용 변수가 되어 null 을 대입할 수 있다.

    // 널 포인터 예외 오류
    var data7: String? = "im"
//    var length2 = data7.length  // 널 포인터 예외 오류
    var length2 = data7?.length     // 널 안정성 호출 연산자 사용

    // ?: 연산자(엘비스 연산자)
    var data8: String? = "im"
    println("data = $data8 : ${data8?.length ?: -1}")   // data8?.length 반환
    data8 = null
    println("data = $data8 : ${data8?.length ?: -1}")   // -1(null 이므로)

    println(some("im"))
    println(some(null))
}