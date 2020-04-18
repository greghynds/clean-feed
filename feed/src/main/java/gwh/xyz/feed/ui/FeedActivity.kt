package gwh.xyz.feed.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import gwh.xyz.feed.R
import gwh.xyz.feed.adapter.FeedController
import gwh.xyz.feed.adapter.FeedPresenter
import gwh.xyz.feed.adapter.FeedTrackingController
import gwh.xyz.feed.adapter.FeedView
import gwh.xyz.feed.domain.FetchFeed
import gwh.xyz.feed.domain.entity.Feed
import gwh.xyz.feed.ui.adapter.HeadlinesAdapter
import gwh.xyz.feed.headline.domain.entity.Headline
import gwh.xyz.feed.headline.ui.HeadlineActivity
import gwh.xyz.tracking.domain.TrackEvent
import kotlinx.android.synthetic.main.activity_feed.*
import org.koin.android.ext.android.get

class FeedActivity : AppCompatActivity(), FeedView {

    private val presenter = FeedPresenter(this, get())
    private val fetchFeed = FetchFeed(get(), presenter)
    private val trackEvent = TrackEvent(get())
    private val feedController = FeedController(fetchFeed, get())
    private val trackingController = FeedTrackingController(trackEvent, get())

    private lateinit var adapter: HeadlinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        configView()
        bindActions()
        fetchFeed()
    }

    override fun onResume() {
        trackingController.resume()
        super.onResume()
    }

    override fun onDestroy() {
        feedController.destroy()
        trackingController.destroy()
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
        headlinesList.visibility = View.VISIBLE
        headlinesProgress.visibility = View.GONE

        Toast.makeText(this, "Couldn't load feed.", Toast.LENGTH_SHORT).show()
    }

    override fun showDetail(headline: Headline) {
        startActivity(HeadlineActivity.intent(this, headline))
    }

    private fun configView() {
        swipeToRefresh.setColorSchemeResources(R.color.colorAccent)
    }

    private fun bindActions() {
        swipeToRefresh.apply {
            setOnRefreshListener {
                fetchFeed()
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

    private fun fetchFeed() {
        feedController.fetchFeed()
        presenter.loading()
    }
}