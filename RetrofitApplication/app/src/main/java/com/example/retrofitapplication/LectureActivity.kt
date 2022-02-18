package com.example.retrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.example.retrofitapplication.databinding.ActivityLectureBinding
import com.example.retrofitapplication.lecture.RetrofitManager
import com.example.retrofitapplication.lecture.onMyTextChanged
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.example.retrofitapplication.lecture.utils.RESPONSE_STATE
import com.example.retrofitapplication.lecture.utils.SEARCH_TYPE
import com.google.android.material.button.MaterialButton

class LectureActivity : AppCompatActivity() {

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO
    private lateinit var binding:ActivityLectureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate() - called")

        // 라디오 그룹 가져오기
        binding.searchTermRadioGroup.setOnCheckedChangeListener{ _, checkId ->
            // switch = when
            when (checkId) {
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "사진검색 버튼을 클릭함")
                    binding.searchTermTextLayout.hint = "사진검색"
                    binding.searchTermTextLayout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_photo_library_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }

                R.id.user_search_radio_btn -> {
                    Log.d(TAG, "사용자 검색 버튼을 클릭함")
                    binding.searchTermTextLayout.hint = "사용자 검색"
                    binding.searchTermTextLayout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_person_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(TAG, "OnCheckedChanged() - called / currentSearchType: $currentSearchType")
        }

        // 텍스트가 변경 되었을때
        binding.searchTermEditText.onMyTextChanged {
            val frame = findViewById<FrameLayout>(R.id.frame_search_btn)
            // 입력된 글자가 하나라도 있는가?
            if (it.toString().count() > 0) {
                frame.visibility = View.VISIBLE

                binding.searchTermTextLayout.helperText = " "
                // 스크롤뷰를 올린다
                binding.mainScrollView.scrollTo(0, 200)
            } else {
                frame.visibility = View.INVISIBLE
            }

            if (it.toString().count() == 12) {
                Log.d(TAG, "onCreate - 에러 띄우기")
                Toast.makeText(this, "검색어는 12자까지 입력이 가능하다", Toast.LENGTH_SHORT).show()
            }
        }

        // 검색 버튼 클릭시
        val buttonSearch = findViewById<MaterialButton>(R.id.button_search)
        buttonSearch.setOnClickListener {
            Log.d(TAG, "onCreate - 검색 버튼이 클릭됨 / currentSearchType : $currentSearchType")

            // 사진 검색 api 호출
            RetrofitManager.instance.searchPhotos(searchTerm = binding.searchTermEditText.toString(), completion = {
                    responseState, responseBody ->
                when(responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api 호출 성공 : $responseBody")
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "api 호출 에러입니다." ,Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 호출 실패 : $responseBody")
                    }
                }
            })

            this.handleSearchButtonUi()
        }
    } // onCreate

    private fun handleSearchButtonUi() {
        val buttonSearch = findViewById<MaterialButton>(R.id.button_search)
        val btnProgress = findViewById<ProgressBar>(R.id.btn_progress)
        btnProgress.visibility = View.VISIBLE
        buttonSearch.text = ""

        Handler().postDelayed({
            btnProgress.visibility = View.INVISIBLE
            buttonSearch.text = "검색"
        }, 1500)
    }
}