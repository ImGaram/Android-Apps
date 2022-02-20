package com.example.retrofitapplication.lecture.utils

import android.content.Context
import android.util.Log
import com.example.retrofitapplication.lecture.App
import com.example.retrofitapplication.lecture.model.SearchData
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import com.google.gson.Gson

object SharedPrefManager {  // 바깥에서도 쓸 수 있게 object 클래스 사용함
    private const val SHARED_SEARCH_HISTORY = "shared_search_history"
    private const val KEY_SEARCH_HISTORY = "key_search_history"

    private const val SHARED_SEARCH_HISTORY_MODE = "shared_search_history_mode"
    private const val KEY_SEARCH_HISTORY_MODE = "key_search_history_mode"

    // 검색어 저장 모드 설정
    fun setSearchHistoryMode(isActivated: Boolean) {
        Log.d(TAG, "SharedPrefManager - setSearchHistoryMode() called / isActivated : $isActivated")

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY_MODE, Context.MODE_PRIVATE)
        // 쉐어드 에디터 가져오기
        val editor = shared.edit()
        editor.putBoolean(KEY_SEARCH_HISTORY_MODE, isActivated)

        editor.apply()  // 변경사항 저장
    }

    // 검색어 저장 모드 확인하기
    fun checkSearchHistoryMode():Boolean {
        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY_MODE, Context.MODE_PRIVATE)

        return shared.getBoolean(KEY_SEARCH_HISTORY_MODE, false)
    }

    // 검색 목록을 저장
    // 문자 배열에다가 gson을 이용해 문자열로 shared에 집어 넣는다.
    fun storeSearchHistoryList(searchStoryList:MutableList<SearchData>) {
        Log.d(TAG, "SharedPrefManager - storeSearchHistoryList() called")

        // 매개변수로 들어온 배열을 문자열로 변환
        val searchHistoryListString: String = Gson().toJson(searchStoryList)
        Log.d(TAG, "SharedPrefManager - storeSearchHistoryList : $searchHistoryListString")

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        // 쉐어드 에디터 가져오기
        val editor = shared.edit()
        editor.putString(KEY_SEARCH_HISTORY, searchHistoryListString)

        editor.apply()  // 변경사항 저장
    }

    // 검색 목록 가져오기
    fun getSearchHistoryList():MutableList<SearchData> {
        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        val storedSearchHistoryListString = shared.getString(KEY_SEARCH_HISTORY, "")!!   // 값이 null 이면 ""을 받겠다

        var storedSearchHistoryList = ArrayList<SearchData>()

        // 검색 목록의 값이 있는가?
        if (storedSearchHistoryListString.isNotEmpty()) {
            // 문자열로 저장된 문자열을 객체 배열로 변경
            storedSearchHistoryList = Gson()
                .fromJson(storedSearchHistoryListString, Array<SearchData>::class.java).toMutableList() as ArrayList<SearchData>
        }

        return storedSearchHistoryList
    }

    // 검색 목록 지우기
    fun clearSearchHistoryList() {
        Log.d(TAG, "clearSearchHistoryList - clearSearchHistoryList() called")

        // 쉐어드 가져오기
        val shared = App.instance.getSharedPreferences(SHARED_SEARCH_HISTORY, Context.MODE_PRIVATE)
        // 쉐어드 에디터 가져오기
        val editor = shared.edit()
        editor.clear()      // 해당 데이터 지우기

        editor.apply()  // 변경사항 저장
    }
}