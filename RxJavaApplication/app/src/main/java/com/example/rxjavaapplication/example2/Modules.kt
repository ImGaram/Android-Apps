package com.example.rxjavaapplication.example2

object Modules {
    data class Result(val query: Query)
    data class Query(val searchInfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}