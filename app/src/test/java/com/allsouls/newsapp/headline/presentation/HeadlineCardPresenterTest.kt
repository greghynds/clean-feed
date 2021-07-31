package com.allsouls.newsapp.headline.presentation

import com.allsouls.newsapp.headline.domain.entity.Headline
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

class HeadlineCardPresenterTest {

    @Test
    fun `sets title when binding headline`() {
        val title = "title"
        val headline = createHeadline(title = title)
        val view = mock<HeadlineCardView>()
        val sut = createPresenter(view)

        sut.bind(headline)

        verify(view).setTitle(title)
    }

    @Test
    fun `sets formatted date when binding headline`() {
        val timestamp = 1574706996877
        val date = Date(timestamp)
        val headline = createHeadline(updated = date)
        val view = mock<HeadlineCardView>()
        val sut = HeadlineCardPresenter(view)

        sut.bind(headline)

        verify(view).setDate("25 November, 2019")
    }

    private fun createHeadline(
        title: String = "title",
        updated: Date = Date(),
        introduction: String = "introduction"
    ): Headline {
        return Headline(title, updated, introduction)
    }

    private fun createPresenter(view: HeadlineCardView): HeadlineCardPresenter {
        return HeadlineCardPresenter(view)
    }
}