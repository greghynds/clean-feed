package com.allsouls.newsapp.arch.presentation



import androidx.lifecycle.ViewModel
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class Presenter(private val dispatchers: Dispatchers) : CoroutineScope, ViewModel() {

    private val job = Job()

    protected open fun onCoroutineError(error: Throwable) {
        // override for handling coroutine exceptions
    }

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job + CoroutineExceptionHandler { _, error ->
            GlobalScope.launch(dispatchers.main) { onCoroutineError(error) }
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

    /**
     * Cancels all running jobs.
     */
    fun destroy() = job.cancel()
}