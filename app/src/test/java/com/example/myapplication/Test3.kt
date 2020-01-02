package com.example.myapplication

import org.junit.Test

class Test3 {

    @Test
    fun test31() {
        val user = User("xq", 18)
        user.name = "第一次修改"
        println("=====================$user")
        val userCopy = user.copy()
        println("userCopy=====================$userCopy")

        user.name = "第二次修改"
        println("=====================$user")
        println("userCopy=====================$userCopy")
    }
}
//=====================User(name=第一次修改, age=18)
//userCopy=====================User(name=第一次修改, age=18)
//=====================User(name=第二次修改, age=18)
//userCopy=====================User(name=第一次修改, age=18)

data class User(var name: String, var age: Int?)
