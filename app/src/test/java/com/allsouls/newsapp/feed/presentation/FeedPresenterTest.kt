package com.allsouls.newsapp.feed.presentation

import com.allsouls.newsapp.arch.data.ApiError
import com.allsouls.newsapp.arch.domain.Params
import com.allsouls.newsapp.arch.presentation.dateFromTimestamp
import com.allsouls.newsapp.feed.domain.FetchFeed
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.util.TestDispatchers
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.util.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class FeedPresenterTest {

    @Test
    fun `shows feed when feed loaded successfully`() {
        runBlocking {
            val text = "headline"
            val updateDateTs = 1448401928L
            val updateDate = Date(updateDateTs)
            val introduction = "introduction"
            val headline = Headline(text, updateDate, introduction)
            val feed = Feed(listOf(headline))
            val fetchFeed = mock<FetchFeed>()
            val view = mock<FeedView>()
            val sut = createPresenter(view, fetchFeed)
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
            val fetchFeed = mock<FetchFeed>()
            val view = mock<FeedView>()
            val sut = createPresenter(view, fetchFeed)
            given(fetchFeed.execute(Params.None)).willReturn(success(feed))

            sut.load()

            verify(view).showFeed(sorted)
        }
    }

    @Test
    fun `shows error when loading feed failed`() {
        runBlocking {
            val error = ApiError(500, "Couldn't load feed.")
            val fetchFeed = mock<FetchFeed>()
            val view = mock<FeedView>()
            val sut = createPresenter(view, fetchFeed)
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
            val view = mock<FeedView>()
            val sut = createPresenter(view)

            sut.selectHeadline(headline)

            verify(view).showDetail(headline)
        }
    }

    @Test
    fun `shows loading indicator when fetching feed`() {
        runBlocking {
            val view = mock<FeedView>()
            val sut = createPresenter(view)

            sut.load()

            verify(view).showLoading()
        }
    }

    private fun createPresenter(view: FeedView, fetchFeed: FetchFeed = mock()): FeedPresenter {
        return FeedPresenter(
            fetchFeed,
            mock(),
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