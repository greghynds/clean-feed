package com.allsouls.newsapp.headline.data

import com.allsouls.newsapp.headline.data.dto.HeadlineDto
import com.github.writethemfirst.approvals.Approvals.verify
import org.junit.Test

class HeadlineAdaptersTest {

    @Test
    fun `maps all fields when converting headline response to headline entity`() {
        val sut = HeadlineDto("headline", 1448401928L, "introduction")

        val result = sut.toEntity()

        verify(result)
    }
}