package com.example.retrofitapplication.class1

import com.example.retrofitapplication.data1.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 통신 인터페이스
interface RetrofitInterface {
    @GET("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getBoxOffice(
        @Query("key") key: String?,
        @Query("targetDt") targetDt: String?,
    ): Call<Result?>?
}