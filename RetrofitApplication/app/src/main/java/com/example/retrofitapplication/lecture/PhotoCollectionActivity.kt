package com.example.retrofitapplication.lecture

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.model.Photo
import com.example.retrofitapplication.lecture.model.SearchData
import com.example.retrofitapplication.lecture.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.example.retrofitapplication.lecture.utils.SharedPrefManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.activity_photo_collection.*
import kotlinx.android.synthetic.main.activity_photo_collection.view.*
import java.util.*
import kotlin.collections.ArrayList

class PhotoCollectionActivity: AppCompatActivity(), SearchView.OnQueryTextListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    // 데이터
    private var photoList = ArrayList<Photo>()
    // 어답터
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter
    // searchView
    private lateinit var mySearchView: SearchView
    // searchView edit text
    private lateinit var mySearchViewEditText:EditText
    // 검색 기록을 남길 배열
    private var searchHistoryList = ArrayList<SearchData>()

    private lateinit var clearSearchHistoryButton:Button
    private lateinit var searchHistorySwitch:SwitchMaterial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")
        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        Log.d(TAG, "PhotoCollectionActivity - onCreate() called / searchTerm : $searchTerm, photoList.count() : ${photoList.count()}")

        searchHistorySwitch = findViewById(R.id.search_history_mode_switch)
        clearSearchHistoryButton = findViewById(R.id.clear_search_history_button)
        searchHistorySwitch.setOnCheckedChangeListener(this)
        clearSearchHistoryButton.setOnClickListener(this)

        val topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        topAppBar.title = searchTerm

        //
        setSupportActionBar(topAppBar)

        this.photoGridRecyclerViewAdapter = PhotoGridRecyclerViewAdapter()
        this.photoGridRecyclerViewAdapter.submitList(photoList)

        val recyclerView = findViewById<RecyclerView>(R.id.my_photo_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,
            2,
            GridLayoutManager.VERTICAL,
            false)
        recyclerView.adapter = this.photoGridRecyclerViewAdapter

        // 저장된 검색기록 가져오기
        this.searchHistoryList = SharedPrefManager.getSearchHistoryList() as ArrayList<SearchData>  // getSearchHistoryList을 arraylist로 받는다.

        this.searchHistoryList.forEach {
            Log.d(TAG, "저장된 검색기록 - it.term : ${it.term}, it.timeStamp : ${it.timeStamp}")
        }
    } // onCreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu - called")
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        this.mySearchView = menu?.findItem(R.id.search_menu_item)?.actionView as SearchView

        // apply : 하나의 변수에 여러 옵션을 넣고 싶을 때 사용
        this.mySearchView.apply {
            this.queryHint = "검색어를 입력하세요"

            this.setOnQueryTextListener(this@PhotoCollectionActivity)

            this.setOnQueryTextFocusChangeListener { _, hasExpanded ->
                val linearSearchHistoryView = this@PhotoCollectionActivity.findViewById<LinearLayout>(R.id.linear_search_history_view)
                when(hasExpanded) {
                    true -> {
                        Log.d(TAG, "search view opened")
                        linearSearchHistoryView.visibility = View.VISIBLE
                    }
                    false -> {
                        Log.d(TAG, "search view closed")
                        linearSearchHistoryView.visibility = View.INVISIBLE
                    }
                }
            }

            // 서치뷰에서 editText를 가져온다
            mySearchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text)
        }

        this.mySearchViewEditText.apply {
            this.filters = arrayOf(InputFilter.LengthFilter(12))    // 글자 수 제한
            this.setTextColor(Color.WHITE)
            this.setHintTextColor(Color.WHITE)
        }

        return true
    }

    // search view 검색어 입력 이벤트
    // 검색 버튼을 클릭 했을 떄
    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d(TAG, "onQueryTextSubmit - called / query : $query")

        val topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        if (!query.isNullOrEmpty()) {   // query에 값이 있는 경우
            topAppBar.title = query

            // TODO:: api 호출
            // TODO:: 검색어 저장

            // 검색 버튼을 누르면 데이터 저장
            val newSearchData = SearchData(term = query, timeStamp = Date().toString())
            this.searchHistoryList.add(newSearchData)   // 배열에 저장
            SharedPrefManager.storeSearchHistoryList(this.searchHistoryList)    // sharedPreference에 데이터 저장
        }
//        this.mySearchView.setQuery("", false)
//        this.mySearchView.clearFocus()      // 키보드를 내림
        topAppBar.collapseActionView() // search view 닫음

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d(TAG, "onQueryTextChange - called / newText : $newText")
//        val userInputText = newText ?: ""

        val userInputText = newText.let {
            it
        }?: ""

        if (userInputText.count() == 12) {
            Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
        }

        return true
    }

    override fun onCheckedChanged(switch: CompoundButton?, isChecked: Boolean) {
        when(switch) {
            searchHistorySwitch -> {
                if (isChecked == true) {
                    Log.d(TAG, "검색어 저장 기능 on")
                } else {
                    Log.d(TAG, "검색어 저장 기능 off")
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            clearSearchHistoryButton -> {
                Log.d(TAG, "검색 기록 삭제 버튼이 클릭됨")
            }
        }
    }
}