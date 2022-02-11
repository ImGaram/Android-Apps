package com.example.retrofitapplication.class1.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//@SerialzedName : JSON으로 serialize 될 때 매칭되는 이름을 명시하는 목적으로 사용
//@Expose : object 중 해당 값이 null일 경우, json으로 만들 필드를 자동 생략
data class BoxOfficeResult (
    @SerializedName("boxofficeType")
    @Expose
    var boxofficeType: String,

    @SerializedName("showRange")
    @Expose
    var showRange: String,

    @SerializedName("dailyBoxOfficeList")
    @Expose
    var dailyBoxOfficeList: List<DailyBoxOfficeList>? = null
)