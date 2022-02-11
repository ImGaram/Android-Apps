package com.example.retrofitapplication.class2.data

import com.example.retrofitapplication.WEATHER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

val num_of_rows = 10
val page_no = 1
val data_type = "JSON"
val base_time = 1100
val base_data = 20200808
val nx = "55"
val ny = "127"

interface WeatherInterface {
    @GET("getVilageFcst?serviceKey=서비스키")
    fun GetWeather(
        @Query("dataType") data_type : String,
        @Query("numOfRows") num_of_rows : Int,
        @Query("pageNo") page_no : Int,
        @Query("base_date") base_date : Int,
        @Query("base_time") base_time : Int,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ): Call<WEATHER> // WEATHER는 DATA CLASS
}