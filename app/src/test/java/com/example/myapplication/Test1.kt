package com.example.myapplication

import org.junit.Test

class Test1 {

    @Test
    fun test1() {
        val list = mutableListOf(1, 2, 3)
        val max1 = maxTest(list) { a, b -> less(a, b) } //推荐写法
        val max2 = maxTest(list, { a, b -> less(a, b) })
        println("===========================================$max1")
        println("===========================================$max2")
    }

    fun less(t1: Int, t2: Int): Boolean {
        return t1 < t2
    }

    /**
     *  获取最大值
     *  (T, T) -> Boolean  : lambda表达式当作参数
     */
    fun <T> maxTest(collection: Collection<T>, less: (T, T) -> Boolean): T? {
        var max: T? = null
        for (it in collection)
            if (max == null || less(max, it))
                max = it
        return max
    }
}

