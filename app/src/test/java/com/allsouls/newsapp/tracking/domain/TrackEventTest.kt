package com.allsouls.newsapp.tracking.domain

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrackEventTest {

    @Mock lateinit var tracker: Tracker

    @Test
    fun `tracks network request event`() {
        runBlocking {
            val time = 100
            val event = Event.NetworkRequest(time)
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
            val sut = TrackEvent(tracker)

            sut.execute(event)

            verify(tracker).track(event)
        }
    }
}