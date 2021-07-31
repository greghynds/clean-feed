package com.allsouls.newsapp.feed.ui.adapter

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.allsouls.newsapp.feed.ui.view.HeadlineCard
import com.allsouls.newsapp.headline.domain.entity.Headline
import org.koin.core.component.KoinComponent

class HeadlinesAdapter(
    private val data: List<Headline>,
    private val onItemClick: (Headline) -> Unit
) : RecyclerView.Adapter<HeadlinesAdapter.ViewHolder>(), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(createHeadlineCard(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        with(holder.card) {
            bind(item)
            setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount() = data.size

    private fun createHeadlineCard(context: Context): HeadlineCard {
        return HeadlineCard(context).apply {
            rootView.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
    }

    class ViewHolder(val card: HeadlineCard) : RecyclerView.ViewHolder(card)
}