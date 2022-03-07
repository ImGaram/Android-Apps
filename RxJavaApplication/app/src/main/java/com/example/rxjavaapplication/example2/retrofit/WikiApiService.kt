package com.example.rxjavaapplication.example2.retrofit

import com.example.rxjavaapplication.example2.Modules
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {
    @GET("api.php")
    fun hitCountWithResponseCode(@Query("action") action: String,
                                 @Query("format") format: String,
                                 @Query("list") list: String,
                                 @Query("srsearch") srsearch: String): Single<Response<Modules.Result>>

    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()
        fun create(): WikiApiService {
            val retrofit = Retrofit.Builder()
                // RxJava2CallAdapterFactory를 넣는 이유는 Observable<> 객체나 Single<> 객체를 리턴할 수 있게 되기 때문이다.
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())

                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://en.wikipedia.org/w/")
                .build()
            return retrofit.create(WikiApiService::class.java)
        }
    }
}