package com.allsouls.newsapp.feed.domain

import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.BDDMockito.given
import java.util.*
import kotlin.Result.Companion.success


class FetchFeedTest {

    @Test
    fun `returns a result when feed fetched successfully`() {
        runBlocking {
            val headline = createHeadline()
            val feed = createFeed(headline)
            val repo = mock<FeedRepo>()
            val sut = FetchFeed(repo)
            given(repo.feed()).willReturn(success(feed))

            val result = sut.execute(Params.None)

            assertThat(result.getOrThrow()).isEqualTo(feed)
        }
    }

    @Test
    fun `returns an error when failed to fetch feed`() {
        runBlocking {
            val headline = createHeadline()
            val feed = createFeed(headline)
            val repo = mock<FeedRepo>()
            val sut = FetchFeed(repo)
            given(repo.feed()).willReturn(success(feed))

            val result = sut.execute(Params.None)

            assertThat(result.getOrThrow()).isEqualTo(feed)
        }
    }

    private fun createFeed(vararg headlines: Headline): Feed {
        return Feed(headlines.toList())
    }

    private fun createHeadline(): Headline {
        return Headline(
            "headline",
            Date(1448401928),
            "introduction"
        )
    }
}