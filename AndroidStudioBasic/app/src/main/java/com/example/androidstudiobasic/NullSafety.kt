package com.example.androidstudiobasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NullSafety : AppCompatActivity() {

    lateinit var lateCar: Car

    class Car(var number:Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_null_safety)

//        //lateInit
//        lateCar= Car(10)
//        Log.d("number","late number : "+lateCar)
//
//        val number:Int = 10
//        val number1:Int? = null
//
//        // !! -> 개발자가 number1이 null 이 아님을 보장함
//        val number5:Int = number1!!+10
//
//        //val number3 = number? +number1
//        val number3 = number1?.plus(number)
//        Log.d("number", "number3 : "+number3)
//
//        // 삼항 연산자 -> 엘비스 연산자
//        // Null Safety 를 위항 도구
//        val number4 = number1 ?:10  //number1 이 null 이면 10 아님 number1
//        Log.d("number","munber4 : "+number4)
    }

    fun plus(a :Int, b:Int?):Int{
        if(b!=null) return a+b
        else return a
    }
    //return type 이 null 일수도 있는 경우
    fun plus2(a:Int, b:Int?):Int?{
        return b?.plus(a)
    }
}