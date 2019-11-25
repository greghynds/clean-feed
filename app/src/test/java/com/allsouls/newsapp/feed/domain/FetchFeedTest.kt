package com.allsouls.newsapp.feed.domain

import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date
import kotlin.Result.Companion.success


@RunWith(MockitoJUnitRunner::class)
class FetchFeedTest {

    @Mock lateinit var repo: FeedRepo

    @Test
    fun `returns a result when feed fetched successfully`() {
        runBlocking {
            val headline = createHeadline()
            val feed = createFeed(headline)
            val sut = createUseCase()
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
            val sut = createUseCase()
            given(repo.feed()).willReturn(success(feed))

            val result = sut.execute(Params.None)

            assertThat(result.getOrThrow()).isEqualTo(feed)
        }
    }

    private fun createUseCase(): FetchFeed {
        return FetchFeed(repo)
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