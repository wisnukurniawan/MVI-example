package com.example.mvstate

import io.reactivex.Observable

interface MviResult
interface MviAction
interface MviIntent
interface ViewState

interface MviViewModel<I : MviIntent, S : ViewState> {
    fun processIntents(intents: Observable<I>)

    fun states(): Observable<S>
}