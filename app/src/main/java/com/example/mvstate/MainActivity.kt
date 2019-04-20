package com.example.mvstate

import android.os.Bundle

class MainActivity2 : BaseViewModelActivity<MainViewModel>() {

    override val vmClass: Class<MainViewModel> by lazy { MainViewModel::class.java }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupView() {
    }
}
