package com.example.rxjavaapplication.example3.retrofit

import com.example.rxjavaapplication.example3.model.CountryModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var instance: CountriesService

    val api: CountriesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountriesApi::class.java)

    fun getInstance():CountriesService {
        if (instance == null) {
            instance = CountriesService()
        }
        return instance
    }

    fun getCountries(): Single<MutableList<CountryModel>> {
        return api.getCountries()
    }
}