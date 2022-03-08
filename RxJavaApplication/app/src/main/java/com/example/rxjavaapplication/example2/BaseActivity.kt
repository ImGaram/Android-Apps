package com.example.rxjavaapplication.example2

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable


open class BaseActivity: AppCompatActivity() {
    var disposable: Disposable? = null

    override fun onPause() {
        super.onPause()
        if (disposable != null) disposable?.dispose()
    }
}