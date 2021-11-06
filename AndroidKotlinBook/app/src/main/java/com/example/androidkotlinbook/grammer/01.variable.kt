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

fun main(){
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
    fun sum(no:Int):Int{
        var sum = 0
        for (i in 1..no) {
            sum+=i
        }
        return sum
    }

    val name: String = "kkang"
//println("name : $name, sum : ${sum(10)}, plus : ${10+20}")

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
    fun some2(): Nothing? {
        return null
    }
    fun some3():Nothing{
        throw Exception()
    }

// 널 허용 / 불허용
    var data5: Int = 10
//data5 = null
    var nully: Int? = 10
    nully = null

    // 함수
    // 방식 : fun 함수명(매개변수명:타입):반환 타입{...}
    // 반환 타입이 있는 함수
    fun some3(data1: Int, data2:Int = 10):Int{
        return data1*data2
        // 함수 안에서 매개변수 값 변경 불가
//        data1 = 20
    }
    // 매개변수 생략 : 순서로 할당
    println(some3(10))
    println(some3(10,20))
    // 매개변수를 지정해 호출
    println(some3(data2 = 20,data1 = 10))

    // 배열
    // Array 클래스의 생성자
    val arr1: Array<Int> = Array(3) { 0 }
    // 배열의 데이터에 접근
    arr1[0] = 10
    arr1[1] = 20
    arr1.set(2,30)  // 배열에서 2번째 데이터를 30으로 설정

    println(
        """
            array size: ${arr1.size}
            array data: ${arr1[0]},${arr1[1]},${arr1.get(2)}
        """.trimIndent()// ${arr1.get(2)}: 2번째 데이터 가져오기
    )

    // 기초 타입 배열
    val arr2: IntArray = IntArray(3) { 0 }
    val arr3: BooleanArray = BooleanArray(3) {false}
    println()

    // arrayOf(): 배열 선언과 동시에 값 할당
    val arr4 = arrayOf<Int>(10,20,30)
    println(
        """
            array size: ${arr4.size}
            array data: ${arr4[0]},${arr4[1]},${arr4.get(2)}
        """.trimIndent()
    )

    // 기초 타입 arrayOf()
    val arr5 = intArrayOf(10,20,30)
    val arr6 = booleanArrayOf(true,false,true)
    println()

    // 리스트
    var list = listOf<Int>(10,20,30)
    println(
        """
            list size: ${list.size}
            list data: ${list[0]},${list[1]},${list.get(2)}
        """.trimIndent()
    )
    println()

    // 가변 리스트
    var mutableList = mutableListOf<Int>(10,20,30)
    mutableList.add(3,40)   // 값 추가
    mutableList.set(0,50)   // 값 추가
    println(
        """
            list size: ${mutableList.size}
            list data: ${mutableList[0]},${mutableList[1]},${mutableList.get(2)},${mutableList.get(2)}
        """.trimIndent()
    )
    println()

    // 집합
    var map = mapOf<String, String>(Pair("one","hello"), "two" to "world")
    println(
        """
            map size: ${map.size}
            map data: ${map.get("one")}, ${map.get("two")}
        """.trimIndent()
    )
}
