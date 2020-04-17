package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

class HeadlinePresenterTrackingTest {

    @Test
    fun `tracks screen view when resuming`() {
        runBlocking {
            val event = Event.Display("headline")
            val trackEvent = mock<TrackEvent>()
            val sut = createPresenter(trackEvent)

            sut.resume()

            verify(trackEvent).send(event)
        }
    }

    private fun createPresenter(trackEvent: TrackEvent): HeadlinePresenter {
        return HeadlinePresenter(
            trackEvent,
            TestDispatchers(),
            mock()
        )
    }
}