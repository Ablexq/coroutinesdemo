package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.test1.Repository.adapterCoroutineQuery
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//private const val TAG: String = "MainActivity"

//private const val TAG: String = "MainActivity"


class MainActivity : AppCompatActivity() {

//    companion object {
//        private const val TAG: String = ""
//    }

    companion object {
        private const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getDataRetrofit()


//        val service = RetrofitFactory.makeRetrofitService()
//        GlobalScope.launch(Dispatchers.Main) {
//            val request = service.getPosts()
//            try {
//                val response = request.await()
//                println("$TAG==============================${response.toString()}")
//                // 主线程更新数据...
//            } catch (e: HttpException) {
//                println("==============================" + e.code())
//            } catch (e: Throwable) {
//                println("==============================Ooops: Something else went wrong")
//            }
//        }
//
//
//        //=================================================================
//
//        GlobalScope.launch(Dispatchers.Main) {
//            println("======== 协程 开始执行，时间:  ${System.currentTimeMillis()}")
//            val token = getToken()
//            val response = getResponse(token)
//            setText(response)
//        }
//
//        //Dispatchers.Main ： 指定协程运行的线程
//        GlobalScope.launch(Dispatchers.Main) {
//
//            // async 返回 Deferred ，通过 Deferred.await 获取 async 的结果
//            val token = GlobalScope.async(Dispatchers.IO) {
//                return@async getToken()
//            }.await()
//
//            val response = GlobalScope.async(Dispatchers.Unconfined) {
//                return@async getResponse(token)
//            }.await()
//
//            setText(response)
//        }

    }

//    suspend fun getToken(): String {
//        delay(300)
//        println("======== getToken 开始执行，时间:  ${System.currentTimeMillis()}")
//        return "ask"
//    }
//
//    suspend fun getResponse(token: String): String {
//        delay(100)
//        println("======== getResponse 开始执行，时间:  ${System.currentTimeMillis()}")
//        return "response"
//    }
//
//    fun setText(response: String) {
//        println("======== setText 执行，时间:  ${System.currentTimeMillis()}")
//    }

    //=======================================================

    private fun getDataRetrofit() {
        presenterScope.launch {
            val time = System.currentTimeMillis()
            try {
                val ganks = adapterCoroutineQuery()
                Log.d("zhaooo", ganks.toString())

                withContext(Dispatchers.Main) {
                    textView.text = ganks[0].who
                }
            } catch (e: Throwable) {

            } finally {
                Log.d("zhaooo", "耗时：${System.currentTimeMillis() - time}")
            }
        }
    }

    private val presenterScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }



    //=======================================================
}


