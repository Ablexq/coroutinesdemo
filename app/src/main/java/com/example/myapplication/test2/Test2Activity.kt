package com.example.myapplication.test2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.coroutines.*

private const val TAG: String = "TestActivity"
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //=================================方式一================================
        /**
         * 单协程内多 suspend 函数运行
         */
        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG,"======== 协程 开始执行，时间:  ${System.currentTimeMillis()}")
            val token = getToken()
            val response = getResponse(token)
            setText(response)
        }

        //=================================方式二================================
        /**
         * 多协程间 suspend 函数运行
         */
        //Dispatchers.Main ： 指定协程运行的线程
        GlobalScope.launch(Dispatchers.Main) {
            // async 返回 Deferred ，通过 Deferred.await 获取 async 的结果
            val token = GlobalScope.async(Dispatchers.IO) {
                return@async getToken()
            }.await()

            val response = GlobalScope.async(Dispatchers.Unconfined) {
                return@async getResponse(token)
            }.await()

            setText(response)
        }
    }

    private suspend fun getToken(): String {
        delay(300)
        Log.e(TAG,"======== getToken 开始执行，时间:  ${System.currentTimeMillis()}")
        return "ask"
    }

    private suspend fun getResponse(token: String): String {
        delay(100)
        Log.e(TAG,"======== getResponse 开始执行，时间:  ${System.currentTimeMillis()}")
        return "response"
    }

    private fun setText(response: String) {
        Log.e(TAG,"======== setText 执行，时间:  ${System.currentTimeMillis()}")
    }

}