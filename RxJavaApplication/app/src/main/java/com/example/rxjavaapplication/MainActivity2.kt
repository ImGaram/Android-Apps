package com.example.rxjavaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.rxjavaapplication.example2.retrofit.WikiApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity2 : AppCompatActivity() {

    var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val TAG = "WikiResultLog"
        var textViewResult: TextView? = null
        val wikiApiService by lazy {
            WikiApiService.create()
        }

        fun showResult(totalhits: Int) {
            Log.d(TAG, "Result hits: $totalhits")
        }

        fun beginSearch(srsearch: String) {
            disposable = wikiApiService.hitCountCheck("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result -> showResult(result.query.searchInfo.totalhits) }
        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}