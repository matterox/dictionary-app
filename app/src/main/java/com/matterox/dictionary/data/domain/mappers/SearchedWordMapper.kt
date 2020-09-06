package com.matterox.dictionary.data.domain.mappers

import com.matterox.dictionary.data.domain.MeaningPreviewModel
import com.matterox.dictionary.data.domain.SearchedWordModel
import com.matterox.dictionary.data.entity.Word
import com.matterox.dictionary.data.helpers.PartOfSpeechMapper

class SearchedWordMapper(
    private val partOfSpeechMapper: PartOfSpeechMapper
) {
    fun transform(word: Word): SearchedWordModel {
        return SearchedWordModel(
            text = word.text,
            meaningPreviews = word.meanings.map {
                MeaningPreviewModel(
                    meaningId = it.id,
                    translation = it.translation.text,
                    translationNote = it.translation.note,
                    transcription = it.transcription,
                    previewUrl = "https:${it.previewUrl}",
                    partOfSpeech = partOfSpeechMapper.transform(it.partOfSpeechCode)
                )
            },
            meaningsId = word.meanings.map {
                it.id
            }
        )
    }
}