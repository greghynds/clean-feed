package com.allsouls.newsapp.feed.domain

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.arch.domain.OutputPort
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.BDDMockito.given
import java.util.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success


class FetchFeedTest {

    @Test
    fun `returns a result when feed fetched successfully`() {
        runBlocking {
            val headline = createHeadline()
            val feed = createFeed(headline)
            val repo = mock<FeedRepo>()
            val output = mock<OutputPort<Feed>>()
            val sut = FetchFeed(repo, output)
            given(repo.feed()).willReturn(success(feed))

            sut.send(Params.None)

            verify(output).receive(success(feed))
        }
    }

    @Test
    fun `returns an error when failed to fetch feed`() {
        runBlocking {
            val repo = mock<FeedRepo>()
            val output = mock<OutputPort<Feed>>()
            val error = ApiError(500, "error")
            val sut = FetchFeed(repo, output)
            given(repo.feed()).willReturn(failure(error))

            sut.send(Params.None)

            verify(output).receive(failure(error))
        }
    }

    @Test
    fun `returns an error when operation throws`() {
        runBlocking {
            val repo = mock<FeedRepo>()
            val output = mock<OutputPort<Feed>>()
            val error = UnknownError("error")
            val sut = FetchFeed(repo, output)
            given(repo.feed()).willThrow(error)

            sut.send(Params.None)

            verify(output).receive(failure(error))
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