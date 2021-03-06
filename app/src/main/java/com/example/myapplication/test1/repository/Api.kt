package com.example.myapplication.test1.repository

import com.example.myapplication.test1.bean.ResultBean
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ApiService {
    //注意这里返回Deferred对象

    @GET("data/iOS/2/1")
    fun getIOSGank(): Deferred<ResultBean>

    @GET("data/Android/2/1")
    fun getAndroidGank(): Deferred<ResultBean>
}

