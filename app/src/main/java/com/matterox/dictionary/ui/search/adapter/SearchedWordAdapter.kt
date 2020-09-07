package com.matterox.dictionary.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matterox.dictionary.R
import com.matterox.dictionary.data.domain.MeaningPreviewModel
import com.matterox.dictionary.data.domain.SearchedWordModel
import com.matterox.dictionary.ui.base.observer
import kotlinx.android.synthetic.main.item_searched_word.view.*

internal class SearchedWordAdapter: RecyclerView.Adapter<SearchedWordAdapter.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()

    var parents: List<SearchedWordModel> by observer(listOf()) {
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.rvMeanings
        val title: TextView = itemView.tvTitle
    }

    var onDebouncedClickListener: ((item: MeaningPreviewModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_searched_word, parent, false)
        )
    }

    override fun getItemCount(): Int = parents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = parents[position]

        holder.title.text = parent.text
        holder.recyclerView.apply {
            layoutManager = GridLayoutManager(holder.recyclerView.context, 2)
            adapter = WordMeaningAdapter().also {
                it.items = parent.meaningPreviews
                it.onDebouncedClickListener = onDebouncedClickListener
            }
            setRecycledViewPool(viewPool)
        }
    }
}