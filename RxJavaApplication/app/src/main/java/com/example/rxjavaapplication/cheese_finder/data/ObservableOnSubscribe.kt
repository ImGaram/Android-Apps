package com.example.rxjavaapplication.cheese_finder.data

import io.reactivex.rxjava3.core.ObservableEmitter
import java.lang.Exception
import kotlin.jvm.Throws

interface ObservableOnSubscribe<T> {
    @Throws(Exception::class)
    fun subscribe(e: ObservableEmitter<T>)
}