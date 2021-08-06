package com.allsouls.newsapp.feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.allsouls.newsapp.arch.presentation.formatDate
import com.allsouls.newsapp.headline.domain.entity.Headline


@Composable
fun HeadlineCard(
    headline: Headline,
    onHeadlineClick: (Headline) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onHeadlineClick(headline) }
    ) {
        Text(
            headline.headline,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp)
        )
        Text(
            formatDate(headline.updated),
            modifier = Modifier.padding(16.dp)
        )
    }
}