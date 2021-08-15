package com.allsouls.newsapp.arch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import xyz.gwh.redux.Action


abstract class Model<State>(private val dispatchers: Dispatchers) : ViewModel() {

    private val state = MutableLiveData<State>()

    abstract fun send(action: Action)

    fun updates(): LiveData<State> = state

    open fun onCoroutineError(error: Throwable) { /* override me */ }

    protected fun emit(newState: State) {
        state.value = newState
    }

    protected fun main(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(
            dispatchers.main + CoroutineExceptionHandler { _, error -> onCoroutineError(error) },
            CoroutineStart.DEFAULT,
            block
        )
    }

    protected suspend fun <T> io(block: suspend CoroutineScope.() -> T): T {
        return withContext(dispatchers.io, block)
    }
}