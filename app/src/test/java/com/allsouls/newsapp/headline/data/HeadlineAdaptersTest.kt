package com.allsouls.newsapp.headline.data

import com.allsouls.newsapp.arch.presentation.dateFromTimestamp
import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.allsouls.newsapp.headline.domain.entity.Headline
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class HeadlineAdaptersTest {

    @Test
    fun `maps all fields when converting headline response to headline entity`() {
        val text = "headline"
        val updateDateTs = 1448401928L
        val updateDate = dateFromTimestamp(updateDateTs)
        val introduction = "introduction"
        val headline = Headline(text, updateDate, introduction)
        val sut = HeadlineDto(text, updateDateTs, introduction)

        val result = sut.toEntity()

        assertThat(result).isEqualToComparingFieldByField(headline)
    }
}