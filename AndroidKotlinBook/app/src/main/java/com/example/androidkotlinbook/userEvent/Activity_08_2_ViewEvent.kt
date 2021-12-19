package com.example.androidkotlinbook.userEvent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidkotlinbook.databinding.Activity082ViewEventBinding
import com.example.androidkotlinbook.javaCode.JavaInterface1
import com.example.androidkotlinbook.javaCode.SAMTest

// 뷰 이벤트
// CompoundButton.OnCheckedChangeListener: 액티비티에서 인터페이스 구현
class Activity_08_2_ViewEvent : AppCompatActivity()/*, CompoundButton.OnCheckedChangeListener*/ {

    private lateinit var binding: Activity082ViewEventBinding
    private lateinit var obj: SAMTest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity082ViewEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 체크박스 이벤트 처리
//        binding.checkBox.setOnCheckedChangeListener(this)

        // checkBox: 이벤트 소스, setOnCheckedChangeListener: 리스너(이벤트 핸들러 등록), object: 이벤트 핸들러
//        binding.checkBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
//            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                Toast.makeText(this@Activity_08_2_ViewEvent, "체크박스 클릭", Toast.LENGTH_SHORT).show()
//            }
//       })

        // SAM 기법 사용
        // SAM 기법 : 코틀린에서 자바 인터페이스를 사용할 수 있게 하기 위해 제공하는 기법
        binding.checkBox.setOnCheckedChangeListener{
            CompoundButton, b ->
            Toast.makeText(this@Activity_08_2_ViewEvent, "체크박스 클릭", Toast.LENGTH_SHORT).show()
        }

        // 버튼의 클릭, 롱 클릭 이벤트 처리
        binding.button.setOnClickListener {
            Toast.makeText(this@Activity_08_2_ViewEvent, "버튼 클릭", Toast.LENGTH_SHORT).show()
        }
        binding.button.setOnLongClickListener {
            Toast.makeText(this@Activity_08_2_ViewEvent, "버튼 길게 클릭", Toast.LENGTH_SHORT).show()
            // 반환값 true 람다 형식은 return 을 쓰지 않는다
            true
        }

        // 코틀린에서 자바 코드 사용
        obj.setInterface(object : JavaInterface1 {
            override fun onClick() {
                println("hello kotlin")
            }
        })
        // SAM 기법을 사용한 자바 함수 호출
        obj.setInterface{ println("helo kotlin") }
    }

//    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        Toast.makeText(this@Activity_08_2_ViewEvent, "체크박스 클릭", Toast.LENGTH_SHORT).show()
//    }
}