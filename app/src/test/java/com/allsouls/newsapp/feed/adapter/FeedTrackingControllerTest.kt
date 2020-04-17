package com.allsouls.newsapp.feed.adapter

import com.allsouls.newsapp.arch.domain.InputPort
import com.allsouls.newsapp.tracking.domain.Event
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FeedTrackingControllerTest {

    @Test
    fun `tracks screen view when resuming`() {
        runBlocking {
            val event = Event.Display("feed")
            val inputPort = mock<InputPort<Event>>()
            val sut = FeedTrackingController(inputPort, TestDispatchers())

            sut.resume()

            verify(inputPort).send(event)
        }
    }
}