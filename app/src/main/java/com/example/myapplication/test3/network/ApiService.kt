package com.example.myapplication.test3.network

import com.example.myapplication.test3.bean.ImageDataResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("image/sogou/api.php")
    suspend fun getImage(@Query("type") type: String = "json"): ImageDataResponseBody
}