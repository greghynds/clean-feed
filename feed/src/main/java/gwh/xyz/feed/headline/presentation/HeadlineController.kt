package gwh.xyz.feed.headline.presentation

import gwh.xyz.core.arch.adapter.Sender
import gwh.xyz.core.arch.presentation.Dispatchers
import gwh.xyz.tracking.domain.Event
import gwh.xyz.tracking.domain.TrackEventPort
import kotlinx.coroutines.launch

class HeadlineController(
    private val trackEvent: TrackEventPort,
    dispatchers: Dispatchers
) : Sender(dispatchers) {

    fun resume() = launch {
        trackEvent.send(Event.Display(SCREEN_NAME))
    }

    companion object {
        private const val SCREEN_NAME = "headline"
    }
}