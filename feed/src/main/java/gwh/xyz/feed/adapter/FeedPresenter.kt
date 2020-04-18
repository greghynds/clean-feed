package gwh.xyz.feed.adapter

import gwh.xyz.core.arch.domain.OutputPort
import gwh.xyz.core.arch.presentation.Dispatchers
import gwh.xyz.core.arch.adapter.Receiver
import gwh.xyz.feed.domain.entity.Feed
import gwh.xyz.feed.headline.domain.entity.Headline
import kotlinx.coroutines.launch

class FeedPresenter(
    private val view: FeedView,
    dispatchers: Dispatchers
) : Receiver(dispatchers), OutputPort<Feed> {

    fun loading() {
        view.showLoading()
    }

    fun selectHeadline(headline: Headline) {
        view.showDetail(headline)
    }

    override suspend fun receive(result: Result<Feed>) {
        launch {
            result.fold(
                { feed -> view.showFeed(sortByDate(feed)) },
                { error -> view.showError(error) }
            )
        }
    }

    private fun sortByDate(feed: Feed): Feed {
        return Feed(feed.headlines.sortedByDescending(Headline::updated))
    }
}