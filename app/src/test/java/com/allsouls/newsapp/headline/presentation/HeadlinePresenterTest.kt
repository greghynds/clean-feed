package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.headline.domain.entity.Headline
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.mockito.Mockito.verify
import java.util.*


class HeadlinePresenterTest {

    @Test
    fun `sets title when rendering headline`() {
        val title = "title"
        val headline = createHeadline(title)
        val view = mock<HeadlineView>()
        val sut = createPresenter(view)

        sut.render(headline)

        verify(view).setTitle(title)
    }

    @Test
    fun `sets date when rendering headline`() {
        val timestamp = 1574706996877
        val date = Date(timestamp)
        val headline = createHeadline(updated = date)
        val view = mock<HeadlineView>()
        val sut = createPresenter(view)

        sut.render(headline)

        verify(view).setDate("25 November, 2019")
    }

    @Test
    fun `sets introduction when rendering headline`() {
        val introduction = "introduction"
        val headline = createHeadline(introduction = introduction)
        val view = mock<HeadlineView>()
        val sut = createPresenter(view)

        sut.render(headline)

        verify(view).setIntroduction(introduction)
    }

    private fun createPresenter(view: HeadlineView): HeadlinePresenter {
        return HeadlinePresenter(
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