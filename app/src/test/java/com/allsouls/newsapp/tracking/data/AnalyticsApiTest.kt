package com.allsouls.newsapp.tracking.data

import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.util.failureResponse
import com.allsouls.newsapp.util.successResponse
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.junit.MockitoJUnitRunner

class AnalyticsApiTest {

    @Test
    fun `returns success when event tracked successfully`() {
        runBlocking {
            val time = 100
            val event = Event.NetworkRequest(time)
            val params = event.toMap()
            val client = mock<AnalyticsClient>()
            val sut = AnalyticsApi(client)
            given(client.track(params)).willReturn(successResponse({}()))

            val result = sut.track(event)

            assertThat(result.isSuccess)
        }
    }

    @Test
    fun `returns error when tracking event failed`() {
        runBlocking {
            val time = 100
            val event = Event.NetworkRequest(time)
            val params = event.toMap()
            val client = mock<AnalyticsClient>()
            val sut = AnalyticsApi(client)
            given(client.track(params)).willReturn(failureResponse("Could not track event", 500))

            val result = sut.track(event)

            assertThat(result.isFailure)
        }
    }
}