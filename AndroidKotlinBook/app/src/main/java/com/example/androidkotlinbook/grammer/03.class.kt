package com.example.androidkotlinbook.grammer

// 클래스
// 선언
class User1 {}

// 클래스의 멤버
class User2 {
    var name = "kkang"
    constructor(name:String){   // 생성자
        this.name = name
    }

    fun someFun() {
        println("name : $name")
    }
    
    class someClass { }
}

class User3 {
    // 주 생성자 선언
    class User3 constructor() { }

    // constructor 생략 가능
//    class User() { }

    // 매개변수가 없는 주 생성자 자동 선언
//    class User {}

    // 주 생성자의 매개변수
    class User(name: String, count: Int) { }
}

// 주 생성자의 본문 init 영역
class User4 {
    class User4(name: String, count: Int) {
        // 주 생성자 본문
    }
//    {  //오류
//        // 클래스 본문
//    }
}

// init 키워드로 주 생성자의 본문 지정
class User5(name: String, count: Int) {
    init {
        println("i am init....")
    }
}

// 생성자의 매개변수를 멤버 변수로 선언하는 방법
class User6(name: String, count: Int) {
    init {
        println("name : $name, count : $count")     // 성공!
    }
    // 생성자를 호출할 때 init 영역이 실행되므로 사용 불가
    fun someFun() {
//        println("name : $name, count : $count")
    }
}

// 생성자의 매개변수를 다른 함수에서 사용하는 예
class User7(name: String, count: Int) {
    // 클래스 멤버 변수 선언
    var name: String
    var count: Int
    init {
        this.name = name
        this.count = count
    }
    fun someFun() {
        println("name : $name, count : $count")
    }
}

// 생성자의 매개변수를 클래스의 멤버 변수로 선언하는 방법(user7 과 같음)
class User8(val name: String, val count: Int) { // 클래스의 멤버 변수(함수에서는 val, var 추가 불가)
    fun someFun() {
        println("name : $name, count : $count")
    }
}

// 보조 생성자 선언
class User9 {
    constructor(name: String) {
        println("constructor(name: String) call...")
    }
    constructor(name: String, count: Int){
        println("constructor(name: String, count: Int) call....")
    }
}

// 보조 생성자에서 주 생성자 호출, 보조 생성자가 여럿일때 생성자 연결
class User10(name: String) {
//    constructor(name: String,count: Int) {  // 오류(주 생성자가 있으므로 보조 생성자에서 주 생성자를 호출해야 함)
    constructor(name: String, count: Int): this(name) { // 주 생성자

    }
    constructor(name: String, count: Int, email: String): this(name, count) {   // 위 생성자

    }
}

// 객체 생성과 멤버 접근
fun main() {
    val user: User2 = User2("kim")  // 생성자는 자동 호출, 생성자가 string 이므로 문자열 데이터 전달
    user.someFun()
    println()

    val user2 = User5("kkang",10)
    println()

    val user3 = User7("im",17)
    user3.someFun()
    println()

    val user4 = User8("imga",17)
    user4.someFun()
    println()

    val user5 = User9("kkang")
    val user6 = User9("kkang",10)
    println()

    val user7 = User10("kkang",10)

    val user8 = User10("kkang",10,"a@a.com")
}
