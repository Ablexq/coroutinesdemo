package com.example.myapplication.test

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//参考：https://blog.csdn.net/yuzhiqiang_1993/article/details/101022254



suspend fun one2(): Int {
    delay(1000)
    return 1
}

//接口B的参数是接口A返回的结果
suspend fun two2(int: Int): Int {
    delay(2000)
    return 2 + int
}


fun main() {
    GlobalScope.launch {
        /*measureTimeMillis返回给定的block代码的执行时间*/
        val time = measureTimeMillis {
            val sum = withContext(Dispatchers.IO) {
                val one2 = one2()
                val two2 = two2(one2)
                one2 + two2
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
//两个方法返回值的和：4
//执行耗时：3039

