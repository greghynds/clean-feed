package com.allsouls.newsapp.feed.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allsouls.newsapp.R
import com.allsouls.newsapp.feed.domain.entity.Headline
import kotlinx.android.synthetic.main.activity_headline.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HeadlineActivity : AppCompatActivity(), HeadlineView {

    private val presenter: HeadlinePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headline)

        val headline = intent.getParcelableExtra<Headline>(KEY_HEADLINE)
        presenter.render(headline)
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