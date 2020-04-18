package gwh.xyz.feed.data

import gwh.xyz.core.arch.data.ApiError
import gwh.xyz.core.arch.presentation.dateFromTimestamp
import gwh.xyz.feed.data.dto.FeedResponse
import gwh.xyz.feed.domain.entity.Feed
import gwh.xyz.feed.headline.data.dto.HeadlineDto
import gwh.xyz.feed.headline.domain.entity.Headline
import gwh.xyz.core.arch.util.failureResponse
import gwh.xyz.core.arch.util.successResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
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
            whenever(client.feed()).thenReturn(successResponse(response))

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
            given(client.feed()).willReturn(failureResponse(errorMsg, code))

            val result = sut.feed()

            assertThat(result.exceptionOrNull())
                .isInstanceOf(ApiError::class.java)
                .hasFieldOrPropertyWithValue("code", code)
                .hasFieldOrPropertyWithValue("message", errorMsg)
        }
    }
}