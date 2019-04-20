package com.example.mvstate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

abstract class BaseViewModelActivity<V : ViewModel> : BaseActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    abstract val vmClass: Class<V>

    val vm: V by lazy {
        ViewModelProviders.of(this, factory).get(vmClass)
    }
}