package com.matterox.dictionary.ui.search.adapter

import android.view.View
import coil.load
import coil.transform.RoundedCornersTransformation
import com.matterox.dictionary.R
import com.matterox.dictionary.data.domain.MeaningPreviewModel
import com.matterox.dictionary.extentions.setOnDebouncedClickListener
import com.matterox.dictionary.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_word_meaning.view.*

internal class WordMeaningAdapter: BaseAdapter<MeaningPreviewModel>(R.layout.item_word_meaning) {
    override fun bind(itemView: View, item: MeaningPreviewModel) {
        itemView.cardView.setOnDebouncedClickListener {
            onDebouncedClickListener?.invoke(item)
        }
        itemView.tvTranslation.text = item.translation
        itemView.tvNote.text = item.translationNote
        itemView.tvTranscription.text = itemView.context
            .getString(R.string.searched_word_transcription, item.transcription)
        itemView.tvPartOfSpeech.text = item.partOfSpeech
        itemView.ivPreview.load(item.previewUrl) {
            transformations(
                RoundedCornersTransformation(
                    radius = 4f
                )
            )
        }
    }
}