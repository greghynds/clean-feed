package com.allsouls.newsapp.feed.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.allsouls.newsapp.R
import com.allsouls.newsapp.feed.domain.entity.Feed
import com.allsouls.newsapp.feed.presentation.FeedPresenter
import com.allsouls.newsapp.feed.presentation.FeedView
import kotlinx.android.synthetic.main.activity_feed.button
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedActivity : AppCompatActivity(), FeedView {

    private val presenter: FeedPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        bindActions()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showFeed(feed: Feed) {
        makeToast("Loaded feed!")
    }

    override fun showError(error: Throwable) {
        makeToast("Couldn't load feed.")
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun bindActions() {
        button.setOnClickListener { presenter.load() }
    }
}