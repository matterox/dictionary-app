package com.matterox.dictionary.ui.meaning.adapter

import android.view.View
import com.matterox.dictionary.R
import com.matterox.dictionary.data.domain.SimilarMeaningTranslation
import com.matterox.dictionary.extentions.setOnDebouncedClickListener
import com.matterox.dictionary.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_similar_translation.view.*

class SimilarMeaningAdapter: BaseAdapter<SimilarMeaningTranslation>(R.layout.item_similar_translation) {
    override fun bind(itemView: View, item: SimilarMeaningTranslation) {
        itemView.tvWord.text = item.word
        itemView.tvTranslation.text = item.translation
        itemView.tvPartOfSpeech.text = item.partOfSpeech
        itemView.tvFrequency.text = itemView.context.getString(R.string.similar_word_frequency, item.frequencyPercent)
        itemView.pbFrequency.progress = item.frequencyPercent.toDouble().toInt()
        itemView.clLayout.setOnDebouncedClickListener {
            onDebouncedClickListener?.invoke(item)
        }
    }
}