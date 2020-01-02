package com.example.myapplication.test

import android.view.View
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) channel.send(x)
//        channel.close() // we're done sending
//    }
//    // here we print received values using `for` loop (until the channel is closed)
//    for (y in channel) println(y)
//    println("Done!")
//}

//协程需要通过构造器来启动协程。官方目前提供的基础构造器有两个：
//
//    launch
//    runBlocking
//
//它们都会启动一个协程，区别在于前者不会阻塞当前线程，并且会返回一个协程的引用，而后者会等待协程的代码执行结束，再执行剩下的代码。
fun main() = runBlocking<Unit> {
    val channel = Channel<String>()
    launch {
        channel.send("A1")
        channel.send("A2")
        log("A done")
    }
    launch {
        channel.send("B1")
        log("B done")
    }
    launch {
        repeat(3) {
            val x = channel.receive()
            log(x)
        }
    }
}
//这儿有个 channel 的概念，顾名思义，它的作用就在于收发事件。
// 调用它的 send 与 receive 方法，就是最简单的使用了。
// 不过要注意，这两个方法会互相等待，所以它们肯定得运行在不同的协程。
fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}





