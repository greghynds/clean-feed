package com.allsouls.newsapp.feed

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.allsouls.newsapp.feed.presentation.*
import com.allsouls.newsapp.feed.ui.FeedUi
import com.allsouls.newsapp.headline.HeadlineActivity
import com.allsouls.newsapp.headline.domain.entity.Headline
import org.koin.android.ext.android.get
import xyz.gwh.redux.createStore

class FeedActivity : AppCompatActivity(), FeedRouter {

    private val store by lazy {
        createStore(
            rootReducer,
            FeedState.empty(),
            logging,
            get(),
            routing(this)
        )
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                FeedUi(store)
            }
        }

        store.dispatch(createLoadFeedAction())
    }

    override fun navigateToHeadline(headline: Headline) {
        startActivity(HeadlineActivity.intent(this, headline))
    }
}