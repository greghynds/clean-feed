package com.allsouls.newsapp.tracking.domain

import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify


class TrackEventTest {

    @Test
    fun `tracks network request event`() {
        runBlocking {
            val time = 100
            val event = Event.NetworkRequest(time)
            val tracker = mock<Tracker>()
            val sut = TrackEvent(tracker)

            sut.execute(event)

            verify(tracker).track(event)
        }
    }

    @Test
    fun `tracks display screen event`() {
        runBlocking {
            val screen = "xxx"
            val event = Event.Display(screen)
            val tracker = mock<Tracker>()
            val sut = TrackEvent(tracker)

            sut.execute(event)

            verify(tracker).track(event)
        }
    }
}