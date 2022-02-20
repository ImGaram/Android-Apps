package com.example.retrofitapplication.lecture.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
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
fun Date.toSimpleString(): String {
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

// 에딧 텍스트 변경을 flow로 받기
fun EditText.textChangesToFlow(): Flow<CharSequence?> {
    // flow 콜백 받기
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Unit
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d(TAG, "onTextChanged() / textChangesToFlow() 에 달려 있는 text watcher / text : $text")
                // 값 내보내기
                trySend(text).isSuccess
            }

            override fun afterTextChanged(p0: Editable?) {
                Unit
            }
        }
        // 위에서 설정한 리스너 달아주기
        addTextChangedListener(listener)

        // 콜백이 사라질때 실행됨
        awaitClose {
            Log.d(TAG, "textChangesToFlow() awaitClose 실행")
            removeTextChangedListener(listener) // 콜백이 사라지면 removeTextChangedListener 샐행
        }
    }.onStart {
        Log.d(TAG, "textChangesToFlow() / onStart 발동")
        // Rx에서 onNext와 동일
        emit(text)  // 에딧 텍스트의 text를 보낸다(이벤트 전달)
    }
}