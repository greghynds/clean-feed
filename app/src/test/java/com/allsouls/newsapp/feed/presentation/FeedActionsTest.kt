package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import xyz.gwh.redux.Action
import java.util.*

class FeedActionsTest {

    @Test
    fun `creates an action for loading the feed`() {
        val expected = Action("LOAD_FEED")

        val result = createLoadFeedAction()

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `creates an action for successfully fetching feed`() {
        val headlines = listOf(Headline("headline", Date(1448401928L), "introduction"))
        val feed = Feed(headlines)
        val expected = Action("LOAD_FEED_SUCCESS", feed)

        val result = createLoadFeedSuccessAction(feed)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `creates an action for failing to fetch feed`() {
        val error = ApiError(500, "Couldn't fetch feed")
        val expected = Action("LOAD_FEED_FAILURE", error)

        val result = createLoadFeedFailureAction(error)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `creates an action for selecting a headline`() {
        val headline = Headline("headline", Date(1448401928L), "introduction")
        val expected = Action("SELECT_HEADLINE", headline)

        val result = createSelectHeadlineAction(headline)

        assertThat(result).isEqualTo(expected)
    }
}