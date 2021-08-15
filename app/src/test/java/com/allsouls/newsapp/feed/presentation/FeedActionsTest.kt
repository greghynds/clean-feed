package com.allsouls.newsapp.feed.presentation

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import xyz.gwh.redux.Action

class FeedActionsTest {

    @Test
    fun `creates an action for loading the feed`() {
        val expected = Action("LOAD_FEED")

        val result = createLoadFeedAction()

        assertThat(result).isEqualTo(expected)
    }
}