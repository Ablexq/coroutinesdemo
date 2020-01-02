package com.example.myapplication

import org.junit.Test

class Test2 {

    @Test
    fun Test21() {
        val age = 18
        val name = "xq"
        ifNotNull(age, name, { age, name -> getStudentAge(age, name) })
        println("======================================================================")

        ifNotNullString(age, name, { age, name -> getStudentName(age, name) })
        println("======================================================================")

        //kotlin 默认协议 有lambda式的 可以变为如下格式，普通参数用小括号 lambda 用大括号隔开
        ifNotNullString(age, name) { age, name -> getStudentName(age, name) }
    }


    /**
     * 泛型有返回值
     */
    fun getStudentName(age: Int?, name: String?): String {
        println("age=${age},name=${name}" + "jack")
        return "jack"
    }

    /**
     * 泛型无返回值
     */
    fun getStudentAge(age: Int?, name: String?) {
        println("age=${age},name=${name}")
    }

    /**
     * (1)传递参数为lambda表达式；无参返回
     * (2)泛型的写法；
     */
    fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
        if (value1 != null && value2 != null) {
            bothNotNull(value1, value2)
        }
    }

    /**
     * (1)传递参数为lambda表达式；有参返回
     * (2)泛型的写法；
     */
    fun <T1, T2> ifNotNullString(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (String)) {
        if (value1 != null && value2 != null) {
            bothNotNull(value1, value2)
        }
    }

}