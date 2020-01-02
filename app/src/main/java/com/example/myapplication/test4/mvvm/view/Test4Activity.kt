package com.example.myapplication.test4.mvvm.view

import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.test4.mvvm.view.base.BaseActivity
import com.example.myapplication.test4.mvvm.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_test4.*

import kotlinx.android.synthetic.main.content_main.*

/**
 *
 */
class Test4Activity : BaseActivity<WeatherViewModel>() {
    override fun layoutId(): Int = R.layout.activity_test4
    override fun providerVMClass(): Class<WeatherViewModel> = WeatherViewModel::class.java

    override fun providerToolBar(): Toolbar = toolbar
    override fun initView() {
        fab.setOnClickListener {
            mViewModel?.getWeather(
                    {
                        progress_bar.visibility = View.VISIBLE
                    },
                    {
                        tv_hello.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                    })
        }
    }

    override fun startObserve() {
        mViewModel?.apply {
            mWeather.observe(this@Test4Activity, Observer { it ->
                tv_hello.text = "${it.data}"

            })
        }
    }

    override fun onDestroy() {
        Log.i("tt", "Test4Activity onDestory")
        super.onDestroy()
    }
}
