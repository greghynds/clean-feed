package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.tracking.domain.TrackEvent
import com.allsouls.newsapp.util.TestDispatchers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HeadlinePresenterTest {

    @Mock lateinit var trackEvent: TrackEvent
    @Mock lateinit var view: HeadlineView

    @Test
    fun `should set title when rendering headline`() {
        val title = "title"
        val headline = createHeadline(title)
        val sut = createPresenter()

        sut.render(headline)

        verify(view).setTitle(title)
    }

    @Test
    fun `should set date when rendering headline`() {
        val timestamp = 1574706996877
        val date = Date(timestamp)
        val headline = createHeadline(updated = date)
        val sut = createPresenter()

        sut.render(headline)

        verify(view).setDate("25 November, 2019")
    }

    @Test
    fun `should set introduction when rendering headline`() {
        val introduction = "introduction"
        val headline = createHeadline(introduction = introduction)
        val sut = createPresenter()

        sut.render(headline)

        verify(view).setIntroduction(introduction)
    }

    private fun createPresenter(): HeadlinePresenter {
        return HeadlinePresenter(
            trackEvent,
            TestDispatchers(),
            view
        )
    }

    private fun createHeadline(
        title: String = "title",
        updated: Date = Date(),
        introduction: String = "introduction"
    ): Headline {
        return Headline(title, updated, introduction)
    }
}