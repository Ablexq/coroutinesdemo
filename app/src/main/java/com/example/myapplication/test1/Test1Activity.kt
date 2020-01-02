package com.example.myapplication.test1

import com.example.myapplication.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.test1.Repository.adapterCoroutineQuery
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

private const val TAG: String = "TestActivity"

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        getDataRetrofit()
    }

    private val scope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }

    private fun getDataRetrofit() {
        scope.launch {
            val timeMillis = measureTimeMillis {
                try {
                    val response = adapterCoroutineQuery()
                    Log.e(TAG, "res===================$response")

                    withContext(Dispatchers.Main) {
                        textView.text = response[0].type
                    }
                } catch (e: Throwable) {
                    Log.e(TAG, "e===============$e")
                }
            }
            Log.e(TAG, "耗时=============${timeMillis}")
        }
    }

    //=======================================================
}


