package com.example.myapplication.test4.repository

import com.example.myapplication.test4.mvvm.model.Weather
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    /**
     * 获取天气信息
     */
    @GET("zaihuishou/Kotlin-mvvm/master/data.json")
    fun getWeather(): Deferred<Weather>
}