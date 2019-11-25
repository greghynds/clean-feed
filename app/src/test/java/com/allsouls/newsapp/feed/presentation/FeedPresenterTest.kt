package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.domain.entity.Headline
import com.allsouls.newsapp.util.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@RunWith(MockitoJUnitRunner::class)
class FeedPresenterTest {

    @Mock lateinit var fetchFeed: FetchFeed
    @Mock lateinit var view: FeedView

    @Test
    fun `shows feed when feed loaded successfully`() {
        runBlocking {
            val text = "headline"
            val updateDateTs = 1448401928L
            val updateDate = Date(updateDateTs)
            val introduction = "introduction"
            val headline = Headline(text, updateDate, introduction)
            val feed = Feed(listOf(headline))
            val sut = createPresenter()
            given(fetchFeed.execute(Params.None)).willReturn(success(feed))

            sut.load()

            verify(view).showFeed(feed)
        }
    }

    @Test
    fun `shows error when loading feed failed`() {
        runBlocking {
            val error = ApiError(500, "Couldn't load feed.")
            val sut = createPresenter()
            given(fetchFeed.execute(Params.None)).willReturn(failure(error))

            sut.load()

            verify(view).showError(error)
        }
    }

    @Test
    fun `shows loading indicator when fetching feed`() {
        runBlocking {
            val sut = createPresenter()

            sut.load()

            verify(view).showLoading()
        }
    }

    private fun createPresenter(): FeedPresenter {
        return FeedPresenter(
            fetchFeed,
            TestDispatchers(),
            view
        )
    }
}