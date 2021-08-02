package com.allsouls.newsapp.feed.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.allsouls.newsapp.feed.presentation.FeedModel
import com.allsouls.newsapp.feed.presentation.createLoadFeedAction
import com.allsouls.newsapp.feed.ui.composable.FeedUi
import org.koin.android.ext.android.inject

class FeedActivity : AppCompatActivity() {

    private val model: FeedModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                FeedUi(model)
            }
        }

        model.send(createLoadFeedAction())
    }
}