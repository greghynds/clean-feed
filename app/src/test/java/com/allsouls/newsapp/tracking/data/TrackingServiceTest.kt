package com.allsouls.newsapp.tracking.data

import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.util.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrackingServiceTest {

    @Mock lateinit var trackEvent: TrackEvent

    @Test
    fun `should send tracking request when tracking event`() {
        runBlocking {
            val event = Event.NetworkRequest(100)
            val sut = createService()

            sut.track(event)

            verify(trackEvent).execute(event)
        }
    }

    private fun createService(): TrackingService {
        return TrackingService(trackEvent, TestDispatchers())
    }
}