package com.example.myapplication.test1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

    suspend fun adapterCoroutineQuery(): List<Result> {
        return withContext(Dispatchers.IO) {
            try {
                val androidDeferred = ApiSource.callAdapterInstance.getAndroidGank()

                val iosDeferred = ApiSource.callAdapterInstance.getIOSGank()

                val androidResult = androidDeferred.await().results

                val iosResult = iosDeferred.await().results

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
