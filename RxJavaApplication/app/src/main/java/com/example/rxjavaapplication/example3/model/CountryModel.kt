package com.example.rxjavaapplication.example3.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("name")
    var countryName:String,
    @SerializedName("capital")
    var capital:String,
    @SerializedName("flagPNG")
    var flag:String
)