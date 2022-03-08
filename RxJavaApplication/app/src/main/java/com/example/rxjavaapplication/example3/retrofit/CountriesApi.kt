package com.example.rxjavaapplication.example3.retrofit

import com.example.rxjavaapplication.example3.model.CountryModel
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<MutableList<CountryModel>>
}