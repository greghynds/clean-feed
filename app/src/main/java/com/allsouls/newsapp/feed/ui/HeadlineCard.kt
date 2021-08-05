package com.allsouls.newsapp.feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.allsouls.newsapp.headline.domain.entity.Headline


@Composable
fun HeadlineCard(
    headline: Headline,
    onHeadlineClick: (Headline) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp, 8.dp, 4.dp)
            .clickable { onHeadlineClick(headline) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(headline.headline)
        }
    }
}