package gwh.xyz.core.arch.adapter


import gwh.xyz.core.arch.presentation.Dispatchers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class Sender(private val dispatchers: Dispatchers) : CoroutineScope {

    private val job = Job()

    protected open fun onCoroutineError(error: Throwable) {}

    override val coroutineContext: CoroutineContext
        get() = dispatchers.io + job + CoroutineExceptionHandler { _, error ->
            GlobalScope.launch(dispatchers.io) { onCoroutineError(error) }
        }

    fun destroy() = job.cancel()
}