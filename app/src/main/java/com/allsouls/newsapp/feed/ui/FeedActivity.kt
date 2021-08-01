package com.allsouls.newsapp.feed.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.Observer
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.presentation.FeedModel
import com.allsouls.newsapp.feed.presentation.FeedView
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.ui.HeadlineActivity
import com.gwh.routes.Route
import com.gwh.routes.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedActivity : AppCompatActivity(), FeedView {

    private val model: FeedModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                FeedScreen(model)
            }
        }

        model.routes.observe(this, { route -> startActivity(route) })
        model.load()
    }

    override fun showLoading() {

    }

    override fun showFeed(feed: Feed) {

    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, "Couldn't load feed.", Toast.LENGTH_SHORT).show()
    }

    override fun showDetail(headline: Headline) {
        startActivity(HeadlineActivity.intent(this, headline))
    }
}
