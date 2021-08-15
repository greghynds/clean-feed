package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class FeedReducerTest {

    @Test
    fun `state is loading when loading feed`() {
        val state = FeedState.empty()
        val action = createLoadFeedAction()
        val expected = FeedState.loading()

        val result = feedReducer(state, action)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `state includes feed when feed fetched successfully`() {
        val state = FeedState.empty()
        val headlines = listOf(Headline("headline", Date(1448401928L), "introduction"))
        val feed = Feed(headlines)
        val action = createLoadFeedSuccessAction(feed)
        val expected = FeedState.from(feed)

        val result = feedReducer(state, action)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `state includes error when feed could not be fetched`() {
        val state = FeedState.empty()
        val error = ApiError(500, "Couldn't fetch feed")
        val action = createLoadFeedFailureAction(error)
        val expected = FeedState.error(error)

        val result = feedReducer(state, action)

        assertThat(result).isEqualTo(expected)
    }
}