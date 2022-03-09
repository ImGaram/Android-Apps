package com.example.rxjavaapplication

import android.os.Bundle
import android.view.View

import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.ButterKnife
import com.example.rxjavaapplication.example3.model.CountryModel
import com.example.rxjavaapplication.example3.recyclerview.CountryListAdapter
import com.example.rxjavaapplication.example3.viewmodel.ListViewModel


class MainActivity3 : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var listError: TextView
    lateinit var loadingView: ProgressBar
    lateinit var refreshLayout: SwipeRefreshLayout

    // 뷰모델은 뷰가 어디 뷰에 사용될지 모른다.
    // 하지만 뷰는 어떤 뷰모델을 사용할지 알아야 한다.
    private lateinit var viewModel: ListViewModel
    private var adapter: CountryListAdapter? = CountryListAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        ButterKnife.bind(this)

        // viewModel 초기화는 viewModelProviders을 통해소 한다
        // 액티비티 destroy 되고 다시 create 되더라도 뷰모델에 있는 데이터를 보여주기 위함이다.
        // 액티비티가 다시 생성되더라도 이전에 생성한 뷰모델 인스턴스을 줄 수 있다.
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // 국가 데이터를 가져온다.
        viewModel.refresh()

        listError = findViewById(R.id.list_error)
        loadingView = findViewById(R.id.loading_view)
        refreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.countriesList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener {    // 새로고침 리스너
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observerViewModel()
    }

    fun observerViewModel() {
        /**
         *  뷰(메인 화면)에 라이브 데이터를 붙인다.
         *  메인 화면에서 관찰할 데이터를 설정한다.
         *  라이브 데이터가 변경됐을 때 변경된 데이터를 가지고 UI를 변경한다.
         */
        viewModel.countries.observe(this
        ) { countryModels: List<CountryModel>? ->

            // 데이터 값이 변할 때마다 호출된다.
            if (countryModels != null) {
                recyclerView.visibility = View.VISIBLE
                // adapter가 리스트를 수정한다.
                adapter?.updateCountries(countryModels as MutableList<CountryModel>)
            }
        }

        viewModel.countryLoadError.observe(this) { isError ->
            // 에러 메시지를 띄운다
            if (isError != null) {
                listError.visibility = if (isError) View.VISIBLE else View.GONE
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            if (isLoading != null) {
                // 로딩 중이라는 것을 보여준다
                loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE

                if (isLoading) {
                    listError.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }
        }
    }
}