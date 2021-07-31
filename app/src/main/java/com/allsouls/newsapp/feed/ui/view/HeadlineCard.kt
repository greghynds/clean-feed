package com.allsouls.newsapp.feed.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.allsouls.newsapp.R
import com.allsouls.newsapp.headline.domain.entity.Headline
import com.allsouls.newsapp.headline.presentation.HeadlineCardPresenter
import com.allsouls.newsapp.headline.presentation.HeadlineCardView
import kotlinx.android.synthetic.main.layout_headline_card.view.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
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
        headlineCardTitleText.text = title
    }

    override fun setDate(date: String) {
        headlineCardDateText.text = date
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        headlineCardRoot.setOnClickListener(listener)
    }

    private fun initialise() {
        inflate()
        onFinishInflate()
    }

    private fun inflate() {
        LayoutInflater.from(context).inflate(R.layout.layout_headline_card, this, true)
    }
}