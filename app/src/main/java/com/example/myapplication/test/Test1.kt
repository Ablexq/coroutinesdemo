package com.example.myapplication

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//参考：https://blog.csdn.net/yuzhiqiang_1993/article/details/101022254

//协程的暂停完全由程序控制，线程的阻塞状态是由操作系统内核来进行切换。
//
//因此，协程的开销远远小于线程的开销。

//对操作系统来说，线程是最小的执行单元，进程是最小的资源管理单元。

//假如这两个接口之间没有联系，我们想让他们并发执行的话，我们可以使用async和await配合使用，代码如下
suspend fun one(): Int {
    delay(1500)
    return 1
}

suspend fun two(): Int {
    delay(1500)
    return 2
}

fun main() {
    //kotlin 中 GlobalScope 类提供了几个携程构造函数：
    //launch - 创建协程
    GlobalScope.launch {
        /*measureTimeMillis返回给定的block代码的执行时间*/
        val time = measureTimeMillis {
            //withContext - 不创建新的协程，在指定协程上运行代码块
            val sum = withContext(Dispatchers.IO) {
                val one = async { one() }
                val two = async { two() }
                one.await() + two.await()
            }
            println("两个方法返回值的和：${sum}")
        }
        println("执行耗时：${time}")
    }
    println("----------------")
    /** 因为上面的协程代码并不会阻塞掉线程，
     *  所以我们这里让线程睡4秒，保证线程的存活，在实际的Android开发中无需这么做
     */
    Thread.sleep(4000)
}
//----------------
//两个方法返回值的和：3
//执行耗时：1567


