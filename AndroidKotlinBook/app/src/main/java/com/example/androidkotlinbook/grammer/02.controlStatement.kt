package com.example.androidkotlinbook.grammer

// 조건문
fun main() {
    var data = 10
    if (data>10){
        println("data > 10")
    }
    // else if 조건 여러 개 나열
    else if (data > 0 && data <=10){
        println("data > 0 && data <=10")
    }else{
        println("data < 0")
    }
    println()

    // if else 를 표현식으로 사용
    val result = if (data>0) {
        println("data > 0")
        true
    }
    else {
        println("data <= 0")
        false
    }
    println(result)
    println()

    // 조건문 when
    when(data){
        10 -> println("data is 10")
        20 -> println("data is 20")
        else -> {
            println("data is not valid data")
        }
    }

    // 문자열을 조건으로 사용 가능
    var data2 = "hello"
    when(data2) {
        "hello" -> println("data is hello")
        "world" -> println("data is world")
        else -> {
            println("data is not valid data")
        }
    }
    println()

    // 다양한 유형의 조건
    var data3: Any = 10
    when(data3) {
        is String -> println("data3 is String")     // data3 가 문자열 타입이면(is = 타입 확임)
        20, 30 -> println("data3 is 20 or 30")      // data3 가 20 또는 30이면
        in 0..10 -> println("data3 is 0..10")       // data3 가 1~10의 값이면(in = 범위 지정)
        else -> println("data is not valid data")
    }
    println()

    // 조건만 명시
    when {
        data <= 0 -> println("data is 0")
        data > 100 -> println("data is > 100")
        else -> println("data is not valid data")
    }

    // 표현식으로 사용
    var result2 = when {
        data <= 0 -> "data is 0"
        data > 100 -> "data is > 100"
        else -> "data is valid"
    }
    println(result2)
    println()

    // 반복문
    var sum: Int  = 0
    for (i in 1..10)
        sum+=i
    println(sum)
    println()

    // for 문 작성 방법
    for (i in 1..10){ }         // 1부터 10까지 1씩 증가
    for (i in 1 until 10){ }    // 1부터 9까지 1씩 증가(10은 미포함)
    for (i in 2..10 step 2){ }  // 2부터 10까지 2씩 증가
    for (i in 10 downTo 1){ }   // 10부터 1까지 1씩 감소

    // 반복 조건에 컬렉션 타입 활용
    var data4 = arrayOf<Int>(10,20,30)
    for (i in data4.indices) {
        print(data4[i])
        if (i != data4.size -1) print(",")
    }
    println()
    println()

    // withIndex() => 인덱스와 데이터를 가져옴
    for ((index, value) in data4.withIndex()) {
        print(value)
        if (index != data4.size -1) print(',')
    }
    println()
    println()

    // while 반복문
    var x = 0
    var sum1 = 0
    while(x<10) {
        sum1+=++x
    }
    println(sum1)
}