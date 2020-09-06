package com.matterox.dictionary.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Model>(
    @LayoutRes val layout: Int
) :
    RecyclerView.Adapter<BaseAdapter<Model>.BaseHolder>() {

    var items: List<Model> by observer(listOf()) {
        notifyDataSetChanged()
    }

    var onDebouncedClickListener: ((item: Model) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return BaseHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    abstract fun bind(itemView: View, item: Model)

    inner class BaseHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Model) {
            bind(itemView, item)
        }
    }
}