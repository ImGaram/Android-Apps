package com.example.rxjavaapplication.cheese_finder.data

interface Emitter<T> {
    fun onNext(value: T)
    fun onError(error: Throwable)
    fun onComplete()
}