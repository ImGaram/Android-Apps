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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.R
import com.example.retrofitapplication.lecture.model.Photo
import com.example.retrofitapplication.lecture.model.SearchData
import com.example.retrofitapplication.lecture.recyclerview.ISearchHistoryRecyclerView
import com.example.retrofitapplication.lecture.recyclerview.PhotoGridRecyclerViewAdapter
import com.example.retrofitapplication.lecture.recyclerview.SearchHistoryRecyclerViewAdapter
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.example.retrofitapplication.lecture.utils.RESPONSE_STATUS
import com.example.retrofitapplication.lecture.utils.SharedPrefManager
import com.example.retrofitapplication.lecture.utils.toSimpleString
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_photo_collection.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class PhotoCollectionActivity: AppCompatActivity(), SearchView.OnQueryTextListener,
    CompoundButton.OnCheckedChangeListener, View.OnClickListener, ISearchHistoryRecyclerView {  // 1. 인터페이스 implement (activity -> searchHistoryRecyclerviewAdapter -> SearchItemViewHolder)
    // 데이터
    private var photoList = ArrayList<Photo>()
    // 어답터
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter
    private lateinit var mySearchHistoryRecyclerViewAdapter: SearchHistoryRecyclerViewAdapter
    // searchView
    private lateinit var mySearchView: SearchView
    // searchView edit text
    private lateinit var mySearchViewEditText:EditText
    // 검색 기록을 남길 배열
    private var searchHistoryList = ArrayList<SearchData>()

    private lateinit var clearSearchHistoryButton:Button
    private lateinit var searchHistorySwitch:SwitchMaterial

    // 옵저버블 통합 제거를 위한 CompositeDisposable
    private var myCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("array_bundle")
        val searchTerm = intent.getStringExtra("search_term")
        photoList = bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        searchHistorySwitch = findViewById(R.id.search_history_mode_switch)
        clearSearchHistoryButton = findViewById(R.id.clear_search_history_button)
        searchHistorySwitch.setOnCheckedChangeListener(this)
        clearSearchHistoryButton.setOnClickListener(this)

        searchHistorySwitch.isChecked = SharedPrefManager.checkSearchHistoryMode()

        val topAppBar = findViewById<MaterialToolbar>(R.id.top_app_bar)
        topAppBar.title = searchTerm

        // 액티비티에서 어떤 액션 바를 쓸지 설정
        setSupportActionBar(topAppBar)

        Log.d(TAG, "PhotoCollectionActivity - onCreate() called / searchTerm : $searchTerm, photoList.count() : ${photoList.count()}")

        // 사진 recyclerview 준비
        this.photoCollectionRecyclerViewSetting(this.photoList)

        // 저장된 검색기록 가져오기
        this.searchHistoryList = SharedPrefManager.getSearchHistoryList() as ArrayList<SearchData>  // getSearchHistoryList을 arraylist로 받는다.

        this.searchHistoryList.forEach {
            Log.d(TAG, "저장된 검색기록 - it.term : ${it.term}, it.timeStamp : ${it.timeStamp}")
        }
        handleSearchViewUi()

        // 검색 기록 recyclerview 준비
        this.searchHistoryRecyclerViewSetting(this.searchHistoryList)

        if (searchTerm!!.isNotEmpty()) {
            val term = searchTerm.let {
                it
            }?:""
            this.insertSearchTermHistory(term)
        }
    } // onCreate

    override fun onDestroy() {
        // 모두 삭제
        this.myCompositeDisposable.clear()
        super.onDestroy()
    }

    // 검색 기록 recyclerview 준비
    private fun searchHistoryRecyclerViewSetting(searchHistoryList: ArrayList<SearchData>) {
        Log.d(TAG, "PhotoCollectionActivity - searchHistoryRecyclerViewSetting() called")

        this.mySearchHistoryRecyclerViewAdapter = SearchHistoryRecyclerViewAdapter(this)    // 2. adapter의 매개변수로 this(인터페이스) 를 보냄
        this.mySearchHistoryRecyclerViewAdapter.submitList(searchHistoryList)

        val myLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        myLinearLayoutManager.stackFromEnd = true   // true로 recyclerview 최신순 정렬

        val recyclerView = this.findViewById<RecyclerView>(R.id.search_history_recycler_view)
        recyclerView.apply {
            layoutManager = myLinearLayoutManager
            this.scrollToPosition(mySearchHistoryRecyclerViewAdapter.itemCount -1)  // item이 맨 위로 오게 함
            adapter = mySearchHistoryRecyclerViewAdapter
        }
    }

    // 그리드 사진 recyclerview 준비
    private fun photoCollectionRecyclerViewSetting(photoList: ArrayList<Photo>) {
        Log.d(TAG, "PhotoCollectionActivity - photoCollectionRecyclerViewSetting() called")

        this.photoGridRecyclerViewAdapter = PhotoGridRecyclerViewAdapter()
        this.photoGridRecyclerViewAdapter.submitList(photoList)

        val recyclerView = findViewById<RecyclerView>(R.id.my_photo_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,
            2,
            GridLayoutManager.VERTICAL,
            false)
        recyclerView.adapter = this.photoGridRecyclerViewAdapter
    }

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
//                        linearSearchHistoryView.visibility = View.VISIBLE
                        handleSearchViewUi()
                    }
                    false -> {
                        Log.d(TAG, "search view closed")
                        linearSearchHistoryView.visibility = View.INVISIBLE
                    }
                }
            }

            // 서치뷰에서 editText를 가져온다
            mySearchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text)

            // searchedEditText 옵저버블
            val editTextChangedObservable = mySearchViewEditText.textChanges()  // editText의 글자가 바뀔때마다 옵저버블이 생김
            val searchEditTextSubscription: Disposable = editTextChangedObservable  // 옵저버블을 통해서 연산자들을 추가함(필터링)
                .debounce(1000, TimeUnit.MILLISECONDS)   // 글자가 입력되고 0.8초 뒤에 onNext 이벤트로 데이터 보내기
                // IO 쓰레드에서 돌리겠다
                // Scheduler instance intended for IO-bound work.
                // 네트워크 요청, 파일 읽기, 쓰기, 디비처리 등
                .subscribeOn(Schedulers.io()).subscribeBy(  // 구독을 통한 이벤트 응답
                    onNext = {
                        Log.d("RX", "onNext : $it")
                        // TODO:: 흘러들어온 이벤트 데이터로 api 호출
                        if (it.isNotEmpty()) {
                            searchPhotoApiCall(it.toString())
                        }
                    },
                    onComplete = { Log.d("RX", "onCompleted") },
                    onError = { Log.d("RX", "onError : $it") }
                )
            // 살아있는 옵저버블을 compositeDisposable에 추가해 나중에 제거
            myCompositeDisposable.add(searchEditTextSubscription)
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

            this.insertSearchTermHistory(query)
            this.searchPhotoApiCall(query)
        }
//        this.mySearchView.setQuery("", false)
//        this.mySearchView.clearFocus()      // 키보드를 내림
        topAppBar.collapseActionView() // search view 닫음

        return true
    }

    // 글자가 입력 될때 발동되는 곳
    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d(TAG, "onQueryTextChange - called / newText : $newText")
//        val userInputText = newText ?: ""

        val userInputText = newText.let {
            it
        }?: ""

        if (userInputText.count() == 12) {
            Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
        }

        if (userInputText.length in 1..12) {    // 입력받은 글자 길이가 1~12 사이이다
            searchPhotoApiCall(userInputText)   // api 호출
        }

        return true
    }

    override fun onCheckedChanged(switch: CompoundButton?, isChecked: Boolean) {
        when(switch) {
            searchHistorySwitch -> {
                if (isChecked == true) {
                    Log.d(TAG, "검색어 저장 기능 on")
                    SharedPrefManager.setSearchHistoryMode(isActivated = true)
                } else {
                    Log.d(TAG, "검색어 저장 기능 off")
                    SharedPrefManager.setSearchHistoryMode(isActivated = false)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            clearSearchHistoryButton -> {   // 검색 기록 전체 삭제
                Log.d(TAG, "검색 기록 삭제 버튼이 클릭됨")
                SharedPrefManager.clearSearchHistoryList()
                this.searchHistoryList.clear()
                // ui 처리
                handleSearchViewUi()
            }
        }
    }

    // 검색 아이템삭제 버튼 이벤트
    override fun onSearchItemDeleteClicked(position: Int) {
        Log.d(TAG, "PhotoCollectionActivity - onSearchItemDeleteClicked() called / position $position")
        // TODO:: 해당 번째의 아이템을 삭제하고 다시 저장
        this.searchHistoryList.removeAt(position)   // 리스트에서 지우고
        SharedPrefManager.storeSearchHistoryList(this.searchHistoryList)    // sharedPreference에서 덮어씌운다

        this.mySearchHistoryRecyclerViewAdapter.notifyDataSetChanged()  // 데이터가 변경되었음을 알린다
        handleSearchViewUi()    // ui 처리
    }

    // 검색 아이템 버튼 이벤트
    override fun onSearchItemClicked(position: Int) {
        Log.d(TAG, "PhotoCollectionActivity - onSearchItemClicked() called / position $position")
        // TODO:: 해당 아이템의 검색어로 api 호출

        val queryString = this.searchHistoryList[position].term
        searchPhotoApiCall(queryString)

        top_app_bar.title = queryString

        this.insertSearchTermHistory(queryString)
        this.top_app_bar.collapseActionView()   // 레이아웃 닫기
    }

    // 사진 검색 api 호출
    private fun searchPhotoApiCall(query: String) {
        RetrofitManager.instance.searchPhotos(searchTerm = query, completion = { status, list ->
            when(status) {
                RESPONSE_STATUS.OKAY -> {
                    Log.d(TAG, "PhotoCollectionActivity - searchPhotoApiCall() called 응답 성공 / list.size : ${list?.size}")

                    // 리스트가 안 비어 있으면
                    if (list != null) {
                        this.photoList.clear()  // 1. 리스트 클리어
                        this.photoList = list   // 2. 빈 리스트에 새 리스트 추가
                        this.photoGridRecyclerViewAdapter.submitList(this.photoList)    // 3. 어답터에 리스트를 보낸다
                        this.photoGridRecyclerViewAdapter.notifyDataSetChanged()        // 4. 데이터가 수정되었을을 알린다
                    }
                }
                RESPONSE_STATUS.NO_CONTENT -> {
                    Toast.makeText(this, "$query 에 대한 검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun handleSearchViewUi() {
        Log.d(TAG, "PhotoCollectionActivity - handleSearchViewUi() called / size : ${this.searchHistoryList.size}")

        val searchHistoryRecyclerView = findViewById<RecyclerView>(R.id.search_history_recycler_view)
        val searchHistoryRecyclerViewLabel = findViewById<TextView>(R.id.search_history_recycler_view_label)
        val clearSearchHistoryBtn = findViewById<Button>(R.id.clear_search_history_button)
        if (this.searchHistoryList.size > 0) {
            searchHistoryRecyclerView.visibility = View.VISIBLE
            searchHistoryRecyclerViewLabel.visibility = View.VISIBLE
            clearSearchHistoryBtn.visibility = View.VISIBLE
        } else {
            searchHistoryRecyclerView.visibility = View.INVISIBLE
            searchHistoryRecyclerViewLabel.visibility = View.INVISIBLE
            clearSearchHistoryBtn.visibility = View.INVISIBLE
        }
    }

    // 검색어 저장
    private fun insertSearchTermHistory(searchTerm: String) {
        Log.d(TAG, "PhotoCollectionActivity - insertSearchTermHistory() called")

        // 검색어 저장 버튼이 켜져 있다면
        if (SharedPrefManager.checkSearchHistoryMode() == true) {
            // 중복 아이템 삭제
            var indexListToRemove = ArrayList<Int>()

            this.searchHistoryList.forEachIndexed { index, searchDataItem ->
                if (searchDataItem.term == searchTerm) {    // 검색어가 같다면
                    Log.d(TAG, "index : $index")
                    indexListToRemove.add(index)
                }
            }

            indexListToRemove.forEach {
                this.searchHistoryList.removeAt(it)
            }

            // 새 아이템 넣기
            val newSearchData = SearchData(term = searchTerm, timeStamp = Date().toSimpleString())
            this.searchHistoryList.add(newSearchData)

            // 기존 데이터에 덮어쓰기
            SharedPrefManager.storeSearchHistoryList(this.searchHistoryList)
            this.mySearchHistoryRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}