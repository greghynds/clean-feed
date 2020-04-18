package gwh.xyz.feed.headline.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import gwh.xyz.feed.R
import gwh.xyz.feed.headline.domain.entity.Headline
import gwh.xyz.feed.headline.presentation.HeadlineController
import gwh.xyz.feed.headline.presentation.HeadlinePresenter
import gwh.xyz.feed.headline.presentation.HeadlineView
import gwh.xyz.tracking.domain.TrackEvent
import gwh.xyz.tracking.domain.TrackEventPort
import kotlinx.android.synthetic.main.activity_headline.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HeadlineActivity : AppCompatActivity(), HeadlineView {

    private val presenter: HeadlinePresenter by inject { parametersOf(this) }
    private val trackEvent: TrackEventPort = TrackEvent(get())
    private val controller = HeadlineController(trackEvent, get())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headline)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val headline = intent.getParcelableExtra<Headline>(KEY_HEADLINE)
        presenter.render(headline)
    }

    override fun onResume() {
        super.onResume()
        controller.resume()
    }

    override fun onDestroy() {
        controller.destroy()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setTitle(title: String) {
        headlineTitleText.text = title
    }

    override fun setDate(date: String) {
        headlineDateText.text = date
    }

    override fun setIntroduction(introduction: String) {
        headlineIntroductionText.text = introduction
    }

    companion object {

        private const val KEY_HEADLINE = "HEADLINE"

        fun intent(context: Context, headline: Headline): Intent {
            return Intent(context, HeadlineActivity::class.java).apply {
                putExtra(KEY_HEADLINE, headline)
            }
        }
    }
}