package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.test3.ui.Test3Activity
import com.example.myapplication.test4.mvvm.view.Test4Activity
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG: String = "Test4Activity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivity(Intent(Test4Activity@this, SecondActivity::class.java))
        //或者
        //startActivity(Intent(this@Test4Activity, SecondActivity::class.java))
        //或者
        //startActivity(Intent(this, SecondActivity::class.java))

        btn1.setOnClickListener {
            startActivity(Intent(this@MainActivity, com.example.myapplication.test1.Test1Activity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this@MainActivity, com.example.myapplication.test2.Test2Activity::class.java))
        }
        btn3.setOnClickListener {
            startActivity(Intent(this@MainActivity, Test3Activity::class.java))
        }
        btn4.setOnClickListener {
            startActivity(Intent(this@MainActivity, Test4Activity::class.java))
        }
    }
}


