package com.allsouls.newsapp.tracking.data

import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

class TrackingServiceTest {

    @Test
    fun `should send tracking request when tracking event`() {
        runBlocking {
            val event = Event.NetworkRequest(100)
            val trackEvent = mock<TrackEvent>()
            val sut = TrackingService(trackEvent, TestDispatchers())

            sut.track(event)

            verify(trackEvent).execute(event)
        }
    }
}   