package com.example.androidkotlinbook.grammer

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
}
