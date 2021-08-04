package com.allsouls.newsapp.feed.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class FeedModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val fetchFeed = mock<FetchFeed>()
    private val sut = createViewModel(fetchFeed)
    private val observer = mock<Observer<FeedState>>()

    @Before
    fun `start observing`() {
        sut.updates().observeForever(observer)
    }

    @After
    fun `stop observing`() {
        sut.updates().removeObserver(observer)
    }

    @Test
    fun `emits loading state when loading feed`() {
        runBlocking {
            val state = FeedState(loading = true)
            val action = createLoadFeedAction()

            sut.send(action)

            verify(observer).onChanged(state)
        }
    }

    @Test
    fun `emits state with list of headlines when fetch successful `() {
        runBlocking {
            val headlines = listOf(Headline("headline", Date(1448401928L), "introduction"))
            val state = FeedState(headlines)
            val feed = Feed(headlines)
            val action = createLoadFeedAction()
            given(fetchFeed.execute(Params.None)).willReturn(success(feed))

            sut.send(action)

            verify(observer).onChanged(state)
        }
    }

    @Test
    fun `emits error state when fetch failed `() {
        runBlocking {
            val error = ApiError(404, "Not found")
            val state = FeedState(error = error)
            val action = createLoadFeedAction()
            given(fetchFeed.execute(Params.None)).willReturn(failure(error))

            sut.send(action)

            verify(observer).onChanged(state)
        }
    }

    private fun createViewModel(
        fetchFeed: FetchFeed = mock()
    ): FeedModel {
        return FeedModel(
            fetchFeed,
            TestDispatchers()
        )
    }
}