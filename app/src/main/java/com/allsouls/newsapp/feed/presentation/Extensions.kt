package com.allsouls.newsapp.feed.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable

@SuppressLint("CheckResult")
fun <State> Observable<State>.asLiveData(): LiveData<State> {
    val _stream = MutableLiveData<State>()
    val stream: LiveData<State> = _stream

    subscribe { state ->
        Log.d("Logger", "Received new state: $state")
        _stream.value = state
    }

    return stream
}