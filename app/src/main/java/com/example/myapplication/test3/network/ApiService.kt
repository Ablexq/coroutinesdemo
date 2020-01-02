package com.njp.coroutinesdemo.network

import com.njp.coroutinesdemo.bean.ImageDataResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("image/sogou/api.php")
    suspend fun getImage(@Query("type") type: String = "json"): ImageDataResponseBody
}