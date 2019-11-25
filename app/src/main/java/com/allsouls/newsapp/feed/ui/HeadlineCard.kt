package com.allsouls.newsapp.feed.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.allsouls.newsapp.R
import com.allsouls.newsapp.feed.domain.entity.Headline
import kotlinx.android.synthetic.main.layout_headline_card.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf


class HeadlineCard : FrameLayout, KoinComponent, HeadlineCardView {

    private val presenter: HeadlineCardPresenter by inject { parametersOf(this) }

    constructor(context: Context) : super(context) {
        initialise()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialise()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialise()
    }

    fun bind(headline: Headline) {
        presenter.bind(headline)
    }

    override fun setTitle(title: String) {
        leadingText.text = title
    }

    override fun setDate(date: String) {
        dateText.text = date
    }

    private fun initialise() {
        inflate()
        onFinishInflate()
    }

    private fun inflate() {
        LayoutInflater.from(context).inflate(R.layout.layout_headline_card, this, true)
    }
}