package com.example.retrofitapplication.class1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private var instance: RetrofitClient? = null
    private var retrofitInterface: RetrofitInterface
    private var baseUrl: String = "http://www.kobis.or.kr"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface = retrofit.create(RetrofitInterface::class.java)
    }

    fun getInstance(): RetrofitClient? {
        if (instance == null) {
            instance = RetrofitClient()
        }
        return instance
    }

    fun getRetrofitInterface(): RetrofitInterface {
        return retrofitInterface
    }
}