package com.example.rxjavaapplication.example3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaapplication.example3.model.CountryModel

class ListViewModel: ViewModel() {
    // 사용자에게 보여줄 국가 데이터
    var countries: MutableLiveData<List<CountryModel>> = MutableLiveData()
    // 국가 데이터를 가져오는 것에 성공했는지를 알려주는 데이터
    var countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    // 로딩 중인지를 나타내는 데이터
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val countryModel1 = CountryModel("Korea", "Seoul", "")
        val countryModel2 = CountryModel("China", "Beijing", "")
        val countryModel3 = CountryModel("Japan", "Tokyo", "")

        var list: MutableList<CountryModel> = mutableListOf()
        list.add(countryModel1)
        list.add(countryModel2)
        list.add(countryModel3)
        list.add(countryModel1)
        list.add(countryModel2)
        list.add(countryModel3)
        list.add(countryModel1)
        list.add(countryModel2)
        list.add(countryModel3)

        // liveData에 데이터를 넣어줌
        // 데이터를 관찰하는 뷰에 넣어줌
        countries.value = list
        countryLoadError.value = false
        loading.value = false
    }

//    // 앱이 통신 중에 프로세스가 종료된 경우 메모리 손실을 최소화 하기 위해 백그라운드 스레드에서 통신을 중단한다.
//    override fun onCleared() {
//        super.onCleared()
//        disposable.clear()
//    }
}