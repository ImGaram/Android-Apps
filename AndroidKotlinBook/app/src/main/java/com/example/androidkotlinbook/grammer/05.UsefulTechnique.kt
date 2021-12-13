package com.example.androidkotlinbook.grammer

// 유용한 기법

// 람다 함수 : 익명 함수 정의 기법
// 코틀린에서 고차 함수를 지원하기 때문에 람다 함수를 많이 쓴다

// 람다 함수 호출
fun sum(no1: Int, no2: Int): Int {  // 일반 함수 선언
    return no1+no2
}
val sum1 = {no1: Int,no2: Int -> no1+no2}    // 람다 함수 선언
val sum2 = {no1: Int,no2: Int -> no1+no2} (10,20)   // 람다 함수 선언과 호출

// 매개변수가 없는 람다 함수
val a = {-> println("function call")}
// 화살표 생략 가능
val aa = { println("function call")}

// 람다 오류 : 타입을 식별할 수 없는 경우
//val some2 = { println(it)}    // 오류!
val some2: (Int) -> Unit = { println(it)}   // 성공!
// 람다 함수의 매개변수 타입을 int 로 선언했으므로 it 이 가리키는 데이터가 int 타입임을 알 수 있다

// 함수 타입 선언
// (Int, Int) -> Int = 함수 타입, {no1:Int, no2:Int -> no1+no2} = 함수 내용
val some: (Int, Int) -> Int = {no1:Int, no2:Int -> no1+no2}

// typealias 타입 : 함수 타입, 데이터 타입을 선언한다
typealias MyInt = Int

// 함수 타입 별칭
typealias MyFunType = (Int,Int) -> Boolean

// 고차 함수
fun hofFun(arg:(Int) -> Boolean) : () -> String {
    val result = if (arg(10)){
        "valid"
    } else {
        "invalid"
    }
    return {"hofFun result : $result"}
}

fun main() {
    // 람다 호출
    sum(10,20)

    // 매개변수가 1개인 람다 함수
    val some = {no:Int -> println(no)}  // 기본 선언 방법
    some(10)

    val some1: (Int) -> Unit = { println(it)}   // (Int) -> Unit => 매개변수가 1개인 람다 함수임을 알려줌
    some1(10)

    // 람다 return 오류
//    val some3 ={no1: Int, no2 : Int -> return no1*no2}  // 오류!
    // 람다는 return 을 쓸 수 없다

    // 람다 함수의 반환문
    val some3 = {no1:Int, no2:Int ->
        println("in lambda function")
        no1*no2
    }
    println("result : ${some3(10,20)}")
    println()

    val data1: Int = 10
    val data2: MyInt = 10   // typealias 사용

        val someFun: MyFunType = {no1/*: Int*/, no2/*: Int*/ -> no1 > no2}  // 매개변수 타입 생략 가능
        val someFun2 = {no1: Int, no2: Int -> no1 > no2}  // 변수 선언 시 타입 생략 가능
    println(someFun(10, 20))
    println(someFun(20, 10))
    println()

    val result = hofFun({no -> no > 0})
    println(result())
}