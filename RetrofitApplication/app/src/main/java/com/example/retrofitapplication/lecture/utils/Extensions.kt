package com.example.retrofitapplication.lecture.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

// 문자열이 json 형태인지, json 배열 형태인지
fun String?.isJsonObject():Boolean {
//    if (this?.startsWith("{") == true && this.endsWith("}")) return true
//    else return false
    return this?.startsWith("{") == true && this.endsWith("}")
}

// 문자열이 json 배열인가?
fun String?.isJsonArray():Boolean {
    return this?.startsWith("[") == true && this.endsWith("]")
}

// 날짜 포맷
fun Date.toString(): String {
    val format = SimpleDateFormat("HH:mm:ss")
    return format.format(this)
}

// 에딧 텍스트에 대한 익스텐션
fun EditText.onMyTextChanged(complection: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            complection(p0)
        }

    })
}