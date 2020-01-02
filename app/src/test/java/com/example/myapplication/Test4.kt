package com.example.myapplication

import org.junit.Test


class Test4 {

    class School(var name: String, var id: Int)

    private val list: MutableList<School> by lazy {
        mutableListOf(
            School("A", 1),
            School("B", 2),
            School("C", 3),
            School("D", 4)
        )
    }

    //====================================================
    @Test
    fun Test40() {
        val s = list.map {
            it.name
        }.joinToString(separator = "/")
        println(s)
        //  A/B/C/D
    }

    //====================================================
    // 简化：

    @Test
    fun Test41() {
        list.joinToString(separator = "/") {
            it.name
        }.apply {
            println(this)
            //  A/B/C/D
        }
    }

    //====================================================
    @Test
    fun Test42() {
        list.joinToString(
            separator = "/",
            prefix = "[",
            postfix = "]",
            limit = 2   //限制几个字符串来操作
        ) {
            it.name
        }.apply {
            println(this)
            //  [A/B/...]
        }
    }

    @Test
    fun Test43() {
        list.joinToString(
            separator = "/",
            prefix = "[",   //前缀
            postfix = "]",  //后缀
            limit = 2,      //限制几个字符串来操作
            truncated = "=" //超过的使用什么来代替
        ) {
            it.name
        }.apply {
            println(this)
            //  [A/B/=]
        }
    }
}