package com.example.myapplication.test4.repository

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository {
    private fun getApiService(): Api {
        return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(Api::class.java)
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().apply { addInterceptor(interceptor) }.build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    fun getWeather() = getApiService().getWeather()

}