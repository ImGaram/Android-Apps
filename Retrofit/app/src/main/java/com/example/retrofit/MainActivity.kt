package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object{
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "95dabcdab4c88faa10d407fc8d42cced"
        var lat = "37.445293"
        var lon = "126.785823"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        call.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()
                    Log.d("MainActivity", "result:"+weatherResponse.toString())
                    val cTemp = weatherResponse!!.main!!.temp - 273.15
                    val minTemp = weatherResponse.main!!.temp_min - 273.15
                    val maxTemp = weatherResponse.main!!.temp_max - 273.15
                    val stringBuilder =
                        "지역: "+weatherResponse.sys!!.country+"\n"+
                        "현재기온: " + cTemp + "\n" +
                        "최저기온: " + minTemp + "\n" +
                        "최고기온: " + maxTemp + "\n" +
                        "풍속: " + weatherResponse.wind!!.speed+ "\n" +
                        "일출시간: " + weatherResponse.sys!!.sunrise + "\n" +
                        "일몰시간: " + weatherResponse.sys!!.sunset + "\n"+
                        "아이콘: " + weatherResponse.weather.get(0).icon + "\n"

                    textview.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("MainActivity","result :"+t.message)
            }

        })
    }
}
