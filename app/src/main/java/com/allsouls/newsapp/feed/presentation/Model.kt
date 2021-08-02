package com.allsouls.newsapp.feed.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allsouls.newsapp.arch.presentation.Action
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.gwh.routes.Route
import kotlinx.coroutines.*

abstract class Model<State>(private val dispatchers: Dispatchers) : ViewModel() {

    protected abstract val state: MutableLiveData<State>
    private val routes = MutableLiveData<Route>()
    abstract fun send(action: Action)

    fun updates(): LiveData<State> = state
    fun routes(): LiveData<Route> = routes
    open fun onCoroutineError(error: Throwable) { /* override me */ }

    protected fun emit(newState: State) {
        state.value = newState
    }

    protected fun navigateTo(route: Route) {
        routes.value = route
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