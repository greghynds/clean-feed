package com.allsouls.newsapp.feed.data

import com.allsouls.newsapp.feed.data.dto.FeedResponse
import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.github.writethemfirst.approvals.Approvals.verify
import org.junit.Test


class FeedMappingTest {

    @Test
    fun `maps all fields when converting feed response to feed entity`() {
        val dto = HeadlineDto("headline", 1448401928L, "introduction")
        val sut = FeedResponse(listOf(dto))

        val result = sut.toEntity()

        verify(result)
    }
}