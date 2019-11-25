package com.allsouls.newsapp.feed.ui

import com.allsouls.newsapp.feed.domain.entity.Headline
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class HeadlinePresenterTest {

    @Mock lateinit var view: HeadlineView

    @Test
    fun `should set title when rendering headline`() {
        val title = "title"
        val headline = createHeadline(title)
        val sut = HeadlinePresenter(view)

        sut.render(headline)

        verify(view).setTitle(title)
    }

    @Test
    fun `should set date when rendering headline`() {
        val timestamp = 1574706996877
        val date = Date(timestamp)
        val headline = createHeadline(updated = date)
        val sut = HeadlinePresenter(view)

        sut.render(headline)

        verify(view).setDate("25 November, 2019")
    }

    @Test
    fun `should set introduction when rendering headline`() {
        val introduction = "introduction"
        val headline = createHeadline(introduction = introduction)
        val sut = HeadlinePresenter(view)

        sut.render(headline)

        verify(view).setIntroduction(introduction)
    }

    private fun createHeadline(
        title: String = "title",
        updated: Date = Date(),
        introduction: String = "introduction"
    ): Headline {
        return Headline(title, updated, introduction)
    }
}