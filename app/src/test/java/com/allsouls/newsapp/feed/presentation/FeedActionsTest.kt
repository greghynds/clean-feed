package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.presentation.Action
import com.allsouls.newsapp.headline.domain.entity.Headline
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class FeedActionsTest {

    @Test
    fun `creates an action for loading the feed`() {
        val expected = Action("LOAD_FEED")

        val result = createLoadFeedAction()

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `creates an action for selecting a headline`() {
        val headline = Headline("headline", Date(), "introduction")
        val expected = Action("SELECT_HEADLINE", headline)

        val result = createSelectHeadlineAction(headline)

        assertThat(result).isEqualTo(expected)
    }
}