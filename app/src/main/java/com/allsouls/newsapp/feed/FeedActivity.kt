package com.allsouls.newsapp.feed

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.allsouls.newsapp.AppState
import com.allsouls.newsapp.arch.presentation.Navigator
import com.allsouls.newsapp.feed.presentation.createLoadFeedAction
import com.allsouls.newsapp.feed.ui.FeedUi
import com.gwh.routes.Route
import com.gwh.routes.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import com.github.greghynds.redux.Store

class FeedActivity : AppCompatActivity(), Navigator {

    private val store: Store<AppState> by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                FeedUi(store)
            }
        }

        store.dispatch(createLoadFeedAction())
    }

    override fun navigateTo(route: Route) = startActivity(route)
}