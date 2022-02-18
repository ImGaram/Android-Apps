package com.example.retrofitapplication.lecture

import android.util.Log
import com.example.retrofitapplication.lecture.utils.API
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.example.retrofitapplication.lecture.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    // 싱글턴 적용
    companion object {
        val instance = RetrofitManager()
    }

    // http 콜 만들기
    // retrofit interface 가져오기
    private val httpCall: RetrofitInterface? = RetrofitClient.getClient(API.BASE_URL)?.create(RetrofitInterface::class.java)

    // 사진 검색 api 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATE, String) -> Unit) {

        // searchTerm이 비어 있으면 "" 을 넣어라, 아니면 it 반환
        val term = searchTerm.let {
            it
        }?: ""

        // 위것과 같다
//        val term = searchTerm ?: ""

        val call = httpCall?.searchPhotos(searchTerm = term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / response: ${response.body().toString()}")

                completion(RESPONSE_STATE.OKAY, response.raw().toString())
            }

            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")

                completion(RESPONSE_STATE.FAIL, t.toString())
            }

        })
    }
}