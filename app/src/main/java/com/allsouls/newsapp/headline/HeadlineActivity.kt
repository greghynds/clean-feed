package com.allsouls.newsapp.headline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.ui.HeadlineUi

class HeadlineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContent {
            MaterialTheme {
                HeadlineUi(intent.getParcelableExtra(KEY_HEADLINE))
            }
        }
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

    companion object {
        private const val KEY_HEADLINE = "HEADLINE"

        fun intent(context: Context, headline: Headline): Intent {
            return Intent(context, HeadlineActivity::class.java)
                .apply { putExtra(KEY_HEADLINE, headline) }
        }
    }
}