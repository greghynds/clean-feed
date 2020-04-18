package gwh.xyz.feed.adapter

import gwh.xyz.core.arch.adapter.Sender
import gwh.xyz.core.arch.domain.Params.None
import gwh.xyz.core.arch.presentation.Dispatchers
import gwh.xyz.feed.domain.FetchFeedPort
import kotlinx.coroutines.launch

class FeedController(
    private val fetchFeed: FetchFeedPort,
    dispatchers: Dispatchers
) : Sender(dispatchers) {

    fun fetchFeed() = launch {
        fetchFeed.send(params = None)
    }
}