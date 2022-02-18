package com.example.retrofitapplication.lecture

import android.util.Log
import com.example.retrofitapplication.lecture.utils.API
import com.example.retrofitapplication.lecture.utils.Constants.TAG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 싱글턴
object RetrofitClient {
    // 레틀핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null
//    private lateinit var retrofitClient: Retrofit

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl:String): Retrofit? {
        Log.d(TAG,"RetrofitClient - getClient() called")

        // 1. 로깅 인터셉터 추가
        // okHttp 인서턴스 생성
        val client = OkHttpClient.Builder()
        // 로그를 찍기 위한 로깅 인터셉터 추가
        val loggingInterceptor = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
//                Log.d(TAG, "RetrofitClient - log{} called / message: $message")

                when {
                    message.isJsonObject() ->
                        Log.d(TAG, JSONObject(message).toString(4))
                    message.isJsonArray() ->
                        Log.d(TAG, JSONArray(message).toString(4))
                    else -> {
                        try {
                            Log.d(TAG, JSONObject(message).toString(4))
                        }catch (e: Exception) {
                            Log.d(TAG, message)
                        }
                    }
                }
            }
        })

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // 위에서 설정한 loggingInterceptor를 okHttp 클라이언트에 추가
        client.addInterceptor(loggingInterceptor)

        // 2. 기본 파리미터 추가
        val baseParameterIntercepter: Interceptor = (object :Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "RetrofitClient - intercept() called")
                // 오리지날 request
                val originalRequest = chain.request()

                // query 파라미터 추가
                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()
                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                return chain.proceed(finalRequest)
            }
        })

        // 위에서 설정한 기본 파라미터 인터셉터를 okHttp 클라이언트에 추가한다
        client.addInterceptor(baseParameterIntercepter)

        // 커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)     // 10초동안 반응이 없으면 종료
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)   // 실패했을때 재실행 여부

        if (retrofitClient == null) {

            // 레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // 위에서 설정한 클라리언트로 레트로핏 클라이언트 설정한다
                .client(client.build())
                .build()
        }
        return retrofitClient
    }
}