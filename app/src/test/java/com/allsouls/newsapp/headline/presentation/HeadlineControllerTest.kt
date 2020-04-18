package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.tracking.domain.TrackEventPort
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class HeadlineControllerTest {

    @Test
    fun `tracks screen view when resumed`() {
        runBlocking {
            val event = Event.Display("headline")
            val trackEvent = mock<TrackEventPort>()
            val sut = HeadlineController(trackEvent, TestDispatchers())

            sut.resume()

            verify(trackEvent).send(event)
        }
    }
}