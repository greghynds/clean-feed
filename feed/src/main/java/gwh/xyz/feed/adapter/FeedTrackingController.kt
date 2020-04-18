package gwh.xyz.feed.adapter

import gwh.xyz.core.arch.adapter.Sender
import gwh.xyz.core.arch.presentation.Dispatchers
import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.TrackEventPort
import kotlinx.coroutines.launch

class FeedTrackingController(
    private val input: TrackEventPort,
    dispatchers: Dispatchers
) : Sender(dispatchers) {

    fun resume() = launch {
        input.send(Event.Display(SCREEN_NAME))
    }

    companion object {
        private const val SCREEN_NAME = "feed"
    }
}