package com.example.myapplication.test4.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.test4.mvvm.model.Weather
import com.example.myapplication.test4.mvvm.viewmodel.base.BaseViewModel
import com.example.myapplication.test4.repository.HttpRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class WeatherViewModel : BaseViewModel() {

    val mWeather: MutableLiveData<Weather> = MutableLiveData()
    /**
     * @param start 这个方法中可以显示加载进度条等
     * @param finally 可以隐藏进度条等
     */
    fun getWeather(start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    val weather = async(IO) { HttpRepository.getWeather() }.await()
                    mWeather.value = weather.await()
                }
                ,
                {
                    Log.i("tt", "${it.message}")
                }, { finally() }, true)

    }

}