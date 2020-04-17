package com.allsouls.newsapp.feed.adapter

import com.allsouls.newsapp.arch.domain.OutputPort
import com.allsouls.newsapp.arch.presentation.Dispatchers
import com.allsouls.newsapp.arch.adapter.Presenter
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import kotlinx.coroutines.launch

class FeedPresenter(
    private val view: FeedView,
    dispatchers: Dispatchers
) : Presenter(dispatchers), OutputPort<Feed> {

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