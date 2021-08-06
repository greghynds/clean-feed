package com.allsouls.newsapp.feed.data

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.arch.presentation.dateFromTimestamp
import com.allsouls.newsapp.feed.data.dto.FeedResponse
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.util.failureResponse
import com.allsouls.newsapp.util.successResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FeedApiTest {

    @Test
    fun `returns a response when request is successful`() {
        runBlocking {
            val text = "headline"
            val updateDateTs = 1448401928L
            val updateDate = dateFromTimestamp(updateDateTs)
            val introduction = "introduction"
            val headline = Headline(text, updateDate, introduction)
            val dto = HeadlineDto(text, updateDateTs, introduction)
            val feed = Feed(listOf(headline))
            val response = FeedResponse(listOf(dto))
            val client = mock<FeedClient>()
            val sut = FeedApi(client)
            given(client.feed()).willReturn(successResponse(response))

            val result = sut.feed()

            assertThat(result.getOrThrow()).isEqualTo(feed)
        }
    }

    @Test
    fun `returns an error when request is not successful`() {
        runBlocking {
            val errorMsg = "Request failed"
            val code = 500
            val client = mock<FeedClient>()
            val sut = FeedApi(client)
            val expected = ApiError(code, errorMsg)
            given(client.feed()).willReturn(failureResponse(errorMsg, code))

            val result = sut.feed()

            assertThat(result.exceptionOrNull()).isEqualTo(expected)
        }
    }
}