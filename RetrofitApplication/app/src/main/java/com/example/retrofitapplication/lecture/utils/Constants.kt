package com.example.retrofitapplication.lecture.utils

object Constants {
    const val TAG: String = "로그"
}

enum class SEARCH_TYPE {
    PHOTO,
    USER
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API {
    const val BASE_URL: String = "https://api.unsplash.com"
    const val CLIENT_ID: String = "f76-ihEAaAeDS9gCtxKikuWhrdLmwFt2b_lc4RZpPpk"
    const val SEARCH_PHOTOS:String = "/search/photos"
    const val SEARCH_USERS:String = "/search/users"
}