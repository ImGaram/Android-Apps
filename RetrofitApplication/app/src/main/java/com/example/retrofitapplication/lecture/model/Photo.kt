package com.example.retrofitapplication.lecture.model

import java.io.Serializable

data class Photo (
    var thumbnail: String?,
    var author: String?,
    var createdAt: String?,
    var likesCount: Int?
): Serializable     // Serializable: 직렬화가 가능하다.