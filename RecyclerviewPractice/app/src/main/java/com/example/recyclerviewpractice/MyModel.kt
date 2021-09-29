package com.example.recyclerviewpractice

import android.util.Log

class MyModel(var name:String?=null, var profileimg: String?=null) {
    var TAG:String="로그"

    //기본생성자
    init {
        Log.d(TAG,"mymodel-init() called")
    }
}