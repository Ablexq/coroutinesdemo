package com.example.myapplication.test

import kotlinx.coroutines.*

val job: Job = Job()
val scope = CoroutineScope(Dispatchers.Default + job)

fun doSomething(): Deferred<String> = scope.async {
//fun doSomething(): Deferred<String> = GlobalScope.async {
    throw RuntimeException("this is an exception")
    "doSomething..."
}

fun main() {
    try {
        GlobalScope.launch {
            doSomething().await()
        }
    } catch (e: Exception) {

    }

    Thread.sleep(5000)
}

