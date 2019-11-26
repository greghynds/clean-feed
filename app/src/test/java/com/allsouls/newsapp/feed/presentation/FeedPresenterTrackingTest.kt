package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.feed.domain.FetchFeed
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
class FeedPresenterTrackingTest {

    @Mock lateinit var fetchFeed: FetchFeed
    @Mock lateinit var trackEvent: TrackEvent
    @Mock lateinit var view: FeedView

    @Test
    fun `tracks screen view when resuming`() {
        runBlocking {
            val event = Event.Display("feed")
            val sut = createPresenter()

            sut.resume()

            verify(trackEvent).execute(event)
        }
    }

    private fun createPresenter(): FeedPresenter {
        return FeedPresenter(
            fetchFeed,
            trackEvent,
            TestDispatchers(),
            view
        )
    }
}