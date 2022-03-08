package com.example.rxjavaapplication.example3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaapplication.example3.model.CountryModel
import com.example.rxjavaapplication.example3.retrofit.CountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    // 사용자에게 보여줄 국가 데이터
    var countries: MutableLiveData<List<CountryModel>> = MutableLiveData()
    // 국가 데이터를 가져오는 것에 성공했는지를 알려주는 데이터
    var countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    // 로딩 중인지를 나타내는 데이터
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val countriesService: CountriesService = CountriesService().getInstance()
    // os에 의해 프로세스가 죽는 등의 상황에서 Single 객체를 가로채기 위함
    private val disposable: CompositeDisposable = CompositeDisposable()

    private fun fetchCountries() {
        loading.value = true
        disposable.add(countriesService.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<MutableList<CountryModel>>(),
                @NonNull Disposable {
                override fun onSuccess(t: MutableList<CountryModel>) {
                    countries.value = t
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
    }

    // 앱이 통신 중에 프로세스가 종료된 경우 메모리 손실을 최소화 하기 위해 백그라운드 스레드에서 통신을 중단한다.
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}