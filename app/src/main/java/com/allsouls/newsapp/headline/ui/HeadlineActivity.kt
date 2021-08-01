package com.allsouls.newsapp.headline.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.allsouls.newsapp.R
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.presentation.HeadlinePresenter
import com.allsouls.newsapp.headline.presentation.HeadlineView
import kotlinx.android.synthetic.main.activity_headline.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HeadlineActivity : AppCompatActivity(), HeadlineView {

    private val presenter: HeadlinePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headline)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val headline = intent.getParcelableExtra<Headline>(KEY_HEADLINE)
        presenter.render(headline!!)
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
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