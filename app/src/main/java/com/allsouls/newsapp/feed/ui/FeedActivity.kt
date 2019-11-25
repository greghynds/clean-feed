package com.allsouls.newsapp.feed.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allsouls.newsapp.R
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.domain.entity.Headline
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

        bindActions()
        presenter.load()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showLoading() {
        headlinesProgress.visibility = View.VISIBLE
        headlinesList.visibility = View.GONE
    }

    override fun showFeed(feed: Feed) {
        headlinesList.visibility = View.VISIBLE
        headlinesProgress.visibility = View.GONE
        adapter = createAdapter(feed)
        headlinesList.adapter = adapter
        headlinesList.layoutManager = LinearLayoutManager(this)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, "Couldn't load feed.", Toast.LENGTH_SHORT).show()
    }

    override fun showDetail(headline: Headline) {
        startActivity(HeadlineActivity.intent(this, headline))
    }

    private fun bindActions() {
        swipeToRefresh.apply {
            setOnRefreshListener {
                presenter.load()
                isRefreshing = false
            }
        }
    }

    private fun createAdapter(feed: Feed): HeadlinesAdapter {
        return HeadlinesAdapter(
            feed.headlines,
            onItemClick = { headline -> presenter.selectHeadline(headline) }
        )
    }
}