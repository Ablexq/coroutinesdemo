package com.example.myapplication.test1

import android.util.Log
import com.example.myapplication.test1.bean.Result
import com.example.myapplication.test1.repository.Http
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG: String = "Reposity"

object Repository {
    suspend fun adapterCoroutineQuery(): List<Result> {
        return withContext(Dispatchers.IO) {
            try {
                val androidDeferred = Http.callAdapterInstance.getAndroidGank()
                val iosDeferred = Http.callAdapterInstance.getIOSGank()

                val androidResult = androidDeferred.await().results
                val iosResult = iosDeferred.await().results
                Log.e(TAG, "androidResult=======================$androidResult")
                Log.e(TAG, "iosResult===========================$iosResult")

                val result = mutableListOf<Result>().apply {
                    addAll(iosResult)
                    addAll(androidResult)
                }
                result
            } catch (e: Throwable) {
                e.printStackTrace()
                throw e
            }
        }
    }
}
