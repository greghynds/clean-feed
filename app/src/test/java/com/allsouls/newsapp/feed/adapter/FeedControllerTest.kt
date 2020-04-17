package com.allsouls.newsapp.feed.adapter

import com.allsouls.newsapp.arch.domain.InputPort
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.domain.Params.None
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.verify

class FeedControllerTest {

    @Test
    fun `sends params to input port when fetching feed`() {
        runBlocking {
            val inputPort = mock<InputPort<Params>>()
            val sut = FeedController(inputPort, TestDispatchers())

            sut.fetchFeed()

            verify(inputPort).send(params = None)
        }
    }
}