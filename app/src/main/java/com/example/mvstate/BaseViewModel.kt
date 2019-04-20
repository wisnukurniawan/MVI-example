package com.example.mvstate

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    val compositeBag by lazy { CompositeDisposable() }

    override fun onCleared() {
        compositeBag.clear()
        super.onCleared()
    }
}

operator fun CompositeDisposable.plusAssign(other: Disposable): Unit = this.run { add(this) }
