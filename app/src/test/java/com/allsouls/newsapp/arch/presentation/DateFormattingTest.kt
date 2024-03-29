package com.allsouls.newsapp.arch.presentation

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.Date

class DateFormattingTest {

    @Test
    fun `creates date from unix timestamp`() {
        val timestamp = 1448401928L
        val date = Date().apply { time = timestamp * 1000 }

        val result = dateFromTimestamp(timestamp)

        assertThat(result).isEqualTo(date)
    }

    @Test
    fun `formats date to readable string`() {
        val timestamp = 1448401928L
        val date = dateFromTimestamp(timestamp)

        val result = formatDate(date)

        assertThat(result).isEqualTo("24 November, 2015")
    }
}