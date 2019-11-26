package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.headline.presentation.HeadlinePresenter
import com.allsouls.newsapp.headline.presentation.HeadlineView
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
class HeadlinePresenterTrackingTest {

    @Mock lateinit var trackEvent: TrackEvent
    @Mock lateinit var view: HeadlineView

    @Test
    fun `tracks screen view when resuming`() {
        runBlocking {
            val event = Event.Display("headline")
            val sut = createPresenter()

            sut.resume()

            verify(trackEvent).execute(event)
        }
    }

    private fun createPresenter(): HeadlinePresenter {
        return HeadlinePresenter(
            trackEvent,
            TestDispatchers(),
            view
        )
    }
}