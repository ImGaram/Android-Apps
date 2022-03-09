package com.example.rxjavaapplication.example3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaapplication.example3.model.CountryModel
import com.example.rxjavaapplication.example3.retrofit.CountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    // 사용자에게 보여줄 국가 데이터
    var countries: MutableLiveData<List<CountryModel>> = MutableLiveData()
    // 국가 데이터를 가져오는 것에 성공했는지를 알려주는 데이터
    var countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    // 로딩 중인지를 나타내는 데이터
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val countriesService: CountriesService = CountriesService().getInstance()
    // os에 의해 프로세스가 죽는 등의 상황에서
    // Single 객체를 가로채기 위함
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun refresh() {
        fetchCountries()
    }

    // 아이템 넣기
    private fun fetchCountries() {
        loading.value = true
        disposable.add(countriesService.getCountries()  // Single<MutableList<CountryModel>> 반환
            // subscribeOn: android는 네트워크 통신시 에러를 발생시키기 때문에 새로운 스레드에서 서버 api 호출을 한다.
            .subscribeOn(Schedulers.newThread())        // 새로운 스레드에서 통신한다
            // observeOn: 데이터를 줍는 스레드(스케줄러)를 지정한다
            .observeOn(AndroidSchedulers.mainThread())  // 응답 값을 가지고 ui update를 하기 위해 필요함, 메인스레드와 통신하기 위함
            // subscribe: 네트워킹을 통해서 가져온 데이터를 뿌렸으니 줍는다, 성공, 실패했을 시 처리를 한다.
            .subscribeWith(object :DisposableSingleObserver<MutableList<CountryModel>>() {
                override fun onSuccess(countryModel: MutableList<CountryModel>) {
                    countries.value = countryModel
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    countryLoadError.value = true
                    loading.value = false
                    e.printStackTrace()
                }

            })
        )

//        // liveData에 데이터를 넣어줌
//        // 데이터를 관찰하는 뷰에 넣어줌
//        countries.value = list
//        countryLoadError.value = false
//        loading.value = false
    }

    // 앱이 통신 중에 프로세스가 종료된 경우 메모리 손실을 최소화 하기 위해 백그라운드 스레드에서 통신을 중단한다.
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}