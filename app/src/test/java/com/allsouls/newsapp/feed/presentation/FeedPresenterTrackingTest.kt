package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify


class FeedPresenterTrackingTest {

    @Test
    fun `tracks screen view when resuming`() {
        runBlocking {
            val event = Event.Display("feed")
            val trackEvent = mock<TrackEvent>()
            val sut = createPresenter(trackEvent)

//            sut.resume()

            verify(trackEvent).execute(event)
        }
    }

    private fun createPresenter(trackEvent: TrackEvent): FeedModel {
        return FeedModel(
            mock(),
            TestDispatchers()
        )
    }
}