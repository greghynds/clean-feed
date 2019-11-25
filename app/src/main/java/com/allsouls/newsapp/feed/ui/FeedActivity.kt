package com.allsouls.newsapp.feed.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allsouls.newsapp.R
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.presentation.FeedPresenter
import com.allsouls.newsapp.feed.presentation.FeedView
import kotlinx.android.synthetic.main.activity_feed.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedActivity : AppCompatActivity(), FeedView {

    private val presenter: FeedPresenter by inject { parametersOf(this) }

    private lateinit var adapter: HeadlinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        presenter.load()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showFeed(feed: Feed) {
        adapter = createAdapter(feed)
        headlinesList.adapter = adapter
        headlinesList.layoutManager = LinearLayoutManager(this)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, "Couldn't load feed.", Toast.LENGTH_SHORT).show()
    }

    private fun createAdapter(feed: Feed): HeadlinesAdapter {
        return HeadlinesAdapter(
            feed.headlines,
            onItemClick = { /* no op */ }
        )
    }
}