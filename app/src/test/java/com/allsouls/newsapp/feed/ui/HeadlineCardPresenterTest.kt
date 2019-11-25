package com.allsouls.newsapp.feed.ui

import com.allsouls.newsapp.feed.domain.entity.Headline
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class HeadlineCardPresenterTest {

    @Mock lateinit var view: HeadlineCardView

    @Test
    fun `should set title when binding headline`() {
        val title = "title"
        val headline = createHeadline(title = title)
        val sut = HeadlineCardPresenter(view)

        sut.bind(headline)

        verify(view).setTitle(title)
    }

    @Test
    fun `should set formatted date when binding headline`() {
        val timestamp = 1574706996877
        val date = Date(timestamp)
        val headline = createHeadline(updated = date)
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
}