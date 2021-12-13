package com.example.androidkotlinbook.grammer

import java.util.*

// 상속
// 형식
open class Super {}  // 상속 할수 있게 open 키워드 사용
class Sub : Super() {}   // Super 를 상속받아 Sub 클래스 생성

// 매개변수가 있는 상위 클래스의 생성자 호출
open class Super1(name: String) {}
class Sub1(name:String): Super1(name){}

// 하위 클래스에 보조 생성자만 있는 경유 상위 클래스의 생성자 호출
open class Super2(name: String) {}
class Sub2: Super2{
    constructor(name: String): super(name){}
}

// 오버라이딩 - 재정의
open class Super3 {
    var superData = 10
    fun superFun() {
        println("i am superfun : $superData")
    }
    // 같은 함수명으로 하위 클래스에서 새로운 로직을 추가하고 싶을때 사용함
}
class Sub3: Super3()

// 오버라이딩의 예
open class Super4 {
    // 오버라이딩을 할 함수나 변수에 open 을 쓴다 안쓰면 오버라이딩을 할 수 없다
    open var someData = 10
    open fun someFun() {
        println("i am super class function : $someData")
    }
}
class Sub4: Super4() {
    // open 으로 선언한 변수나 함수를 하위 클래스에서 재정의할때는 override 키워드를 쓴다
    override var someData = 20
    override fun someFun() {
        println("i am sub class function : $someData")
    }
}

// 접근 제한자
// public, internal, protected, private
// public : 모든 파일에서 이용 가능
// internal : 같은 모듈 내에서 사용 가능
// protected : 사용 불가 / 상속 관계의 하위 클래스에서 사용 가능
// private : 파일 내부에서 사용 가능 / 클래스 내부에서 사용 가능
open class Super5 {
    var publicData = 10
    protected var protectedData = 20
    private var privateData = 30
}
class Sub5: Super5() {
    fun subFun() {
        publicData++
        protectedData++
//        privateData++
    }
}

// 데이터 클래스
// data 키워드로 선언하며 자주 사용하는 데이터를 객체로 묶을 수 있다.
class NonDataClass(val name: String, val email: String, val age: Int)   // 기본 클래스
data class DataClass(val name: String, val email:String, val age:Int)

// 데이터 클래스의 equals()
data class DataClass2(val name: String, val email: String, val age: Int) {
    lateinit var address: String
    constructor(name: String, email: String, age: Int, address:String):
        this(name,email,age) {
        this.address = address
    }
}

// 오브젝트 클래스
val objact = object {
    var data = 10
    fun some() {
        println("data : $data")
    }
}

// 타입을 지정한 오브젝트 클래스
open class Superr {         // open : 클래스의 상속을 허용
    open var data = 10
    open fun some() {
        println("i am super some() : $data")
    }
}
val objaact = object : Superr() {
    override var data = 10
    override fun some() {
        println("i am object some() : $data")
    }
}

// 컴패니언 클래스
class MyClass {
    companion object {  // 클래스 이름으로 멤버에 접근
        var data = 10
        fun some() {
            println(data)
        }
    }
}

fun main() {
    val obj = Sub3()
    obj.superData = 20  // superData 를 하위 클래스에서(Sub3) 다시 선언함
    obj.superFun()
    println()

    val obj1 = Sub4()
    obj1.someFun()
    println()

    val obj3 = Super5()
    obj3.publicData++
//    obj3.protectedData++
//    obj3.privateData++

    // 데이터 클래스 객체 생성
    val non1 = NonDataClass("im","@.com",17)
    val non2 = NonDataClass("im","@.com",17)

    val data1 = DataClass("im","@.com",17)
    val data2 = DataClass("im","@.com",17)

    // 객체의 데이터 비교
    // equals() : 데이터의 객체들을 비교해 true, false 로 출력
    println("non data class equals : ${non1.equals(non2)}") // 객체 자체를 비교하므로 false
    println("data class equals : ${data1.equals(data2)}")   // 객체의 데이터를 비교하므로 true
    println()

    val obj4 = DataClass2("im","@.com",17,"seoul")
    val obj5 = DataClass2("im","@.com",17,"seoul")
    println("obj4.equals(obj5) : ${obj4.equals(obj5)}")

    // toString(): 객체의 데이터를 반환
    class NonDataClass(val name: String,val email: String, val age: Int)
    data class DataClass(val name: String,val email: String, val age: Int)
    val non = NonDataClass("im","@.com",17)
    val data = DataClass("im","@.com",17)
    println("non data class toString : ${non.toString()}")
    println("data class toString : ${data.toString()}")
    println()

//    objact.data = 20    // 오류! : 타입을 지정하지 않아서
//    objact.some()       // 오류! : 타입을 지정하지 않아서

    objaact.data = 30   // 성공
    objaact.some()      // 성공
    println()


    MyClass.data = 20
    MyClass.some()
}
