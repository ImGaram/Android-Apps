package com.example.androidkotlinbook.grammer

// 타입 지정
//val data1 : Int 오류
var data2 = 10

class User{
//    val data4 : Int 오류
    val data5: Int = 10
}

//fun main() {
////    data1 = 23    val 이라 오류
//    data2 = 233
//}

// 초기화 미루기(초기값을 할당할수 없을 때 사용)
//lateinit var data1: Int 오류: int 는 불가
//lateinit val data22: String   오류: val 은 불가
lateinit var data3 : String
// lateinit 은 var 에서만 사용 가능
// lateinit 는 int, long, short, double,float,boolean,byte 타입은 사용 뷸가

//lazy
val data4: Int by lazy {
    println("in lazy.......")
    10
}

// null
fun someFun(){
    var data1:Int = 10
    var data2:Int? = null   // null 대입 가능

    data1+=10
    data1 = data1.plus(10)  // 객체의 메서드 이용 가능
}

// 기초 데이터 타입
val a1: Byte = 0b00001011

val a2: Int = 123
val a3: Short = 123
val a4: Long = 10L
val a5: Double = 10.0
val a6: Float = 10.0f

val a7: Boolean = true

// 문자 표현
val a: Char = 'a'
//if (a==1){}   오류

// 문자열 표현
val str1 = "hello \n world"
val str2 = """
        hello
        world
    """.trimIndent()    // trimIndent 문자열의 공백음 없앰

// 예
fun main(){
    fun sum(no:Int):Int{
        var sum = 0
        for (i in 1..no) {
            sum+=i
        }
        return sum
    }

    val name: String = "kkang"
    println("name : $name, sum : ${sum(10)}, plus : ${10+20}")
}

// 그 외 타입
// 1.any = 모든 타입 가능
val data1: Any = 10
val data21: Any = "hello"

val data32: Any = User()

// 2.Unit = 반환문이 없는 함수
val data11: Unit = Unit

fun some(): Unit{
    println(10+20)
}
// 반환타입 생략 => 자동으로 unit 적용
fun some1(){
    println(10+20)
}

// 3. nothing = null 이나 예외를 반환
val data123: Nothing? = null
