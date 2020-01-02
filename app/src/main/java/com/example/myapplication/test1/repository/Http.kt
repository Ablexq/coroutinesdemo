package com.example.myapplication.test1.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Http {
    companion object {
        @JvmField
        val callAdapterInstance = Retrofit.Builder()
            .baseUrl("http://gank.io/api/")
            .client(getOkHttpClient())
            //注意这里CoroutineCallAdapterFactory
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        private fun getOkHttpClient(): OkHttpClient {
            //添加一个log拦截器,打印所有的log
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            //可以设置请求过滤的水平,body,basic,headers
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .build()
        }
    }
}