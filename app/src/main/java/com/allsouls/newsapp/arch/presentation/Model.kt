package com.allsouls.newsapp.arch.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class Model(private val dispatchers: Dispatchers) : CoroutineScope, ViewModel() {

    protected open fun onCoroutineError(error: Throwable) {
        // override for handling coroutine exceptions
    }

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + CoroutineExceptionHandler { _, error ->
            viewModelScope.launch(dispatchers.main) { onCoroutineError(error) }
        }

    /**
     * Launches a coroutine that runs in the UI context.
     */
    fun CoroutineScope.main(block: suspend CoroutineScope.() -> Unit): Job {
        return launch(coroutineContext, CoroutineStart.DEFAULT, block)
    }

    /**
     * Executes the [block] in a coroutine that runs in the IO context.
     */
    suspend fun <T> io(block: suspend CoroutineScope.() -> T): T =
        withContext(dispatchers.io, block)

    /**
     * Launches a coroutine that runs in the IO context.
     *
     * This should be used for background tasks that don't need to
     * post a result back to the UI.
     */
    fun CoroutineScope.background(block: suspend CoroutineScope.() -> Unit): Job =
        launch(dispatchers.io, CoroutineStart.DEFAULT, block)
}
