package com.allsouls.newsapp.feed

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.allsouls.newsapp.feed.presentation.FeedDelegate
import com.allsouls.newsapp.feed.presentation.FeedModel
import com.allsouls.newsapp.feed.presentation.createLoadFeedAction
import com.allsouls.newsapp.feed.ui.FeedUi
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.HeadlineActivity
import org.koin.android.ext.android.inject

class FeedActivity : AppCompatActivity(), FeedDelegate {

    private val model: FeedModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                FeedUi(model, this)
            }
        }

        model.send(createLoadFeedAction())
    }

    override fun onHeadlineClick(headline: Headline) {
        startActivity(HeadlineActivity.intent(this, headline))
    }
}