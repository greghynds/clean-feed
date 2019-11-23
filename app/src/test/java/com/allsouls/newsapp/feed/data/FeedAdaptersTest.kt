package com.allsouls.newsapp.feed.data

import com.allsouls.newsapp.feed.data.dto.FeedResponse
import com.allsouls.newsapp.feed.data.dto.HeadlineDto
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.domain.entity.Headline
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.Date

class FeedAdaptersTest {

    @Test
    fun `maps all fields when converting headline response to headline entity`() {
        val text = "headline"
        val updateDateTs = 1448401928L
        val updateDate = Date(updateDateTs)
        val introduction = "introduction"
        val headline = Headline(text, updateDate, introduction)
        val sut = HeadlineDto(text, updateDateTs, introduction)

        val result = sut.toEntity()

        assertThat(result).isEqualToComparingFieldByField(headline)
    }

    @Test
    fun `maps all fields when converting feed response to feed entity`() {
        val text = "headline"
        val updateDateTs = 1448401928L
        val updateDate = Date(updateDateTs)
        val introduction = "introduction"
        val headline = Headline(text, updateDate, introduction)
        val dto = HeadlineDto(text, updateDateTs, introduction)
        val feed = Feed(listOf(headline))
        val sut = FeedResponse(listOf(dto))

        val result = sut.toEntity()

        assertThat(result).isEqualToComparingFieldByField(feed)
    }
}