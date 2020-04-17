package com.allsouls.newsapp.tracking.data

import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEvent
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