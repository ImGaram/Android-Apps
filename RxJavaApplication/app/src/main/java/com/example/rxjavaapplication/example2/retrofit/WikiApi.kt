package com.example.rxjavaapplication.example2.retrofit

import com.example.rxjavaapplication.example2.Modules
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {
    @GET("api.php")
    fun hitCountWithResponseCode(@Query("action") action: String,
                                 @Query("format") format: String,
                                 @Query("list") list: String,
                                 @Query("srsearch") srsearch: String): Single<Response<Modules.Result>>
}