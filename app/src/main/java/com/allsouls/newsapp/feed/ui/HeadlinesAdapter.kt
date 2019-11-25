package com.allsouls.newsapp.feed.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allsouls.newsapp.feed.domain.entity.Headline
import org.koin.core.KoinComponent

class HeadlinesAdapter(
    private val data: List<Headline>,
    private val onItemClick: (Headline) -> Unit
) : RecyclerView.Adapter<HeadlinesAdapter.ViewHolder>(), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HeadlineCard(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        with(holder.card) {
            bind(item)
            setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount() = data.size

    class ViewHolder(val card: HeadlineCard) : RecyclerView.ViewHolder(card)
}