package com.example.appdevelopproject.recyclerview

// 기본값으로 false 를 넣어서 add 할때 생략이 가능하다
data class Todo(
    val text: String,
    var isDone: Boolean = false,
)  // data class 는 자동으로 getter setter 가 구현된다.