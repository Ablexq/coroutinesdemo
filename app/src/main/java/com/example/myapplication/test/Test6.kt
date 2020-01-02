package com.example.myapplication.test

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * async 与 await
 */
fun main() = runBlocking {
    //async 代码块会新启动一个协程后立即执行，并且返回一个 Deferred 类型的值，
    //调用它的 await 方法后会暂停当前协程，直到获取到 async 代码块执行结果，当前协程才会继续执行。
    val result: Deferred<String> = async { doSomethingTimeout() }
    println("I will got the result ${result.await()}")
    //launch和async的异常处理不同.
    //这是因为async返回值, 是期待await调用的, 所以会持有异常, 在调用await()的时候才返回(结果或异常).
    //所以如果await()没有被调用的话, 异常就会被吃了.
}

suspend fun doSomethingTimeout(): String {
    delay(1000)
    return "Result"
}