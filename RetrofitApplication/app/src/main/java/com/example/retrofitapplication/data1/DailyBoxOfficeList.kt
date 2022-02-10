package com.example.retrofitapplication.data1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//@SerialzedName : JSON으로 serialize 될 때 매칭되는 이름을 명시하는 목적으로 사용
//@Expose : object 중 해당 값이 null일 경우, json으로 만들 필드를 자동 생략
data class DailyBoxOfficeList(
    @SerializedName("rnum")
    @Expose
    var rnum: String,

    @SerializedName("rank")
    @Expose
    var rank: String,

    @SerializedName("rankInten")
    @Expose
    var rankInten: String,

    @SerializedName("rankOldAndNew")
    @Expose
    var rankOldAndNew: String,

    @SerializedName("movieCd")
    @Expose
    var movieCd: String,

    @SerializedName("movieNm")
    @Expose
    var movieNm: String,

    @SerializedName("openDt")
    @Expose
    var openDt: String,

    @SerializedName("salesAmt")
    @Expose
    var salesAmt: String,

    @SerializedName("salesShare")
    @Expose
    var salesShare: String,

    @SerializedName("salesInten")
    @Expose
    var salesInten: String,

    @SerializedName("salesChange")
    @Expose
    var salesChange: String,

    @SerializedName("salesAcc")
    @Expose
    var salesAcc:String,

    @SerializedName("audiCnt")
    @Expose
    var audiCnt: String,

    @SerializedName("audiInten")
    @Expose
    var audiInten: String,

    @SerializedName("audiChange")
    @Expose
    var audiChange: String,

    @SerializedName("audiAcc")
    @Expose
    var audiAcc: String,

    @SerializedName("scrnCnt")
    @Expose
    var scrnCnt: String,

    @SerializedName("showCnt")
    @Expose
    var showCnt: String
)
