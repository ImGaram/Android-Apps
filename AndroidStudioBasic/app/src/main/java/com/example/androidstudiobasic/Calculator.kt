package com.example.androidstudiobasic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*

class Calculator : AppCompatActivity() {

    var a: String = ""
    var list = mutableListOf<String>()
    var b: Long = 0L
    var c: Long = 1L
    var d: Double = 1.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        button1.setOnClickListener {
            editText2.setText(editText2.getText().append("1"))
            a = editText2.getText().toString()

        }

        button2.setOnClickListener {
            editText2.setText(editText2.getText().append("2"))
            a = editText2.getText().toString()

        }

        button3.setOnClickListener {
            editText2.setText(editText2.getText().append("3"))
            a = editText2.getText().toString()

        }

        button4.setOnClickListener {
            editText2.setText(editText2.getText().append("4"))
            a = editText2.getText().toString()

        }

        button5.setOnClickListener {
            editText2.setText(editText2.getText().append("5"))
            a = editText2.getText().toString()

        }

        button6.setOnClickListener {
            editText2.setText(editText2.getText().append("6"))
            a = editText2.getText().toString()

        }

        button7.setOnClickListener {
            editText2.setText(editText2.getText().append("7"))
            a = editText2.getText().toString()

        }

        button8.setOnClickListener {
            editText2.setText(editText2.getText().append("8"))
            a = editText2.getText().toString()

        }

        button9.setOnClickListener {
            editText2.setText(editText2.getText().append("9"))
            a = editText2.getText().toString()
        }

        button0.setOnClickListener {
            editText2.setText(editText2.getText().append("0"))
            a = editText2.getText().toString()

        }

        sum_Btn.setOnClickListener {
            editText2.setText(editText2.getText().append("+"))
            a = editText2.getText().toString()
        }

        sub_Btn.setOnClickListener {
            editText2.setText(editText2.getText().append("-"))
            a = editText2.getText().toString()
        }

        mul_Btn.setOnClickListener {
            editText2.setText(editText2.getText().append("X"))
            a = editText2.getText().toString()
        }

        div_Btn.setOnClickListener {
            editText2.setText(editText2.getText().append("/"))
            a = editText2.getText().toString()
        }
        result_Btn.setOnClickListener {
            b = 0
            c = 1
            d = 1.0
            if (list.size != 0) {
                list = mutableListOf<String>()
            }

            if (editText2.getText().contains("+")) {
                val str = editText2.getText().split("+")
                val a = str[0].toDouble()
                val z = a.toInt()
                for (i in 0..str.size - 1) {
                    if(i == 0){
                        list.add(z.toString())
                        b += list.get(i).toInt()
                    }else{
                        list.add(str[i])
                        b += list.get(i).toInt()
                    }

                }

                editText2.setText(String.format("%d", b))
            }

            if (editText2.getText().contains("-")) {
                val str = editText2.getText().split("-")
                val a = str[0].toDouble()
                val z = a.toLong()
                b = z
                for (i in 0..str.size - 1) {
                    list.add(str[i])
                    if (i != 0) {
                        b -= list.get(i).toInt()

                    }
                }

                editText2.setText(String.format("%d", b))
            }

            if (editText2.getText().contains("X")) {
                val str = editText2.getText().split("X")
                val a = str[0].toDouble()
                val z = a.toInt()

                for (i in 0..str.size - 1) {
                    if(i == 0){
                        list.add(z.toString())
                        c *= list.get(i).toInt()
                    }else{
                        list.add(str[i])
                        c *= list.get(i).toInt()
                    }

                }

                editText2.setText(String.format("%d", c))
            }

            if (editText2.getText().contains("/")) {
                val str = editText2.getText().split("/")
                d = str[0].toDouble()
                for (i in 0..str.size - 1) {
                    list.add(str[i])
                    if (i != 0) {
                        d /= list.get(i).toDouble()

                    }
                }

                editText2.setText(String.format("%.2f", d))
            }

        }

        reset_Btn.setOnClickListener {
            editText2.setText("")
        }
    }
}