package gwh.xyz.tracking.data

import gwh.xyz.core.arch.presentation.Dispatchers
import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.TrackEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TrackingService(
    private val trackEvent: TrackEvent,
    private val dispatchers: Dispatchers
) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.io + job + CoroutineExceptionHandler { _, _ ->
            GlobalScope.launch(dispatchers.io) { /* no op */ }
        }

    fun track(event: Event) {
        io { trackEvent.send(event) }
    }

    private fun io(block: suspend CoroutineScope.() -> Unit): Job {
        return launch(coroutineContext, CoroutineStart.DEFAULT, block)
    }
}