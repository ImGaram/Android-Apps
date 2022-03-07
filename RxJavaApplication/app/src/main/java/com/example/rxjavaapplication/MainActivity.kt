package com.example.rxjavaapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subject: BehaviorSubject<String> = BehaviorSubject.createDefault("0")

        val ed = findViewById<EditText>(R.id.ed)
        val tv = findViewById<TextView>(R.id.tv)
        ed.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                subject.map {
                        dan -> ed.text.toString().equals("")
                }
                    .flatMap({
                            dan -> BehaviorSubject.range(1, 9)
                    }) { dan, row -> "0 x $row = 0  \n" }
                    .scan { x, y -> x + y }
                    .subscribe { text -> tv.setText(text) }

                subject.map { dan -> ed.text.toString().toLong() }
                    .flatMap({ dan -> BehaviorSubject.range(1, 9) }
                    ) { dan, row ->
                        "" + dan + " x " + row + " = " + (dan * row) + "\n"
                    }
                    .scan { x, y -> x + y }
                    .doOnNext {
                            data -> Log.d("onNext()", data)
                    }
                    .subscribe({ text -> tv.setText(text) }) { obj: Throwable -> obj.message }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}