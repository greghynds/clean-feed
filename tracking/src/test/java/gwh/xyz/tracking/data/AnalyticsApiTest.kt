package gwh.xyz.tracking.data

import gwh.xyz.tracking.domain.Event
import gwh.xyz.core.arch.util.failureResponse
import gwh.xyz.core.arch.util.successResponse
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.BDDMockito.given

class AnalyticsApiTest {

    @Test
    fun `should return success when event tracked successfully`() {
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
    fun `should return error when tracking event failed`() {
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