package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.dateFromTimestamp
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.util.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@RunWith(MockitoJUnitRunner::class)
class FeedPresenterTest {

    @Mock lateinit var fetchFeed: FetchFeed
    @Mock lateinit var trackEvent: TrackEvent
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
    fun `shows sort headlines by date descending when feed loaded successfully`() {
        runBlocking {
            val feed = createFeed(
                createHeadline(updated = dateFromTimestamp(1448601928)),
                createHeadline(updated = dateFromTimestamp(1459709926)),
                createHeadline(updated = dateFromTimestamp(1448401928L))
            )
            val sorted = createFeed(
                createHeadline(updated = dateFromTimestamp(1459709926)),
                createHeadline(updated = dateFromTimestamp(1448601928)),
                createHeadline(updated = dateFromTimestamp(1448401928L))
            )
            val sut = createPresenter()
            given(fetchFeed.execute(Params.None)).willReturn(success(feed))

            sut.load()

            verify(view).showFeed(sorted)
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
    fun `navigates to detail view when headline selected`() {
        runBlocking {
            val headline = Headline(
                "title",
                Date(),
                "introduction"
            )
            val sut = createPresenter()

            sut.selectHeadline(headline)

            verify(view).showDetail(headline)
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
            trackEvent,
            TestDispatchers(),
            view
        )
    }

    private fun createFeed(vararg headline: Headline): Feed {
        return Feed(headline.toList())
    }

    private fun createHeadline(
        title: String = "title",
        updated: Date = Date(),
        introduction: String = "introduction"
    ): Headline {
        return Headline(title, updated, introduction)
    }
}