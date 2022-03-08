package com.example.rxjavaapplication.example2.retrofit

import io.reactivex.Observable
import retrofit2.http.GET

interface DynamicApi {
    @GET("/")
    fun getAnyInfo(): Observable<String>
}