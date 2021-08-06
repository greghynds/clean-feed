package com.allsouls.newsapp.feed.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.allsouls.newsapp.headline.domain.entity.Headline


@Composable
fun HeadlinesList(
    headlines: List<Headline>,
    onHeadlineClick: (Headline) -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        headlines.forEach { headline ->
            item {
                HeadlineCard(headline, onHeadlineClick)
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}