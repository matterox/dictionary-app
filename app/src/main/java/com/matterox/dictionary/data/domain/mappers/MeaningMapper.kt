package com.matterox.dictionary.data.domain.mappers

import com.matterox.dictionary.data.domain.MeaningModel
import com.matterox.dictionary.data.domain.SimilarMeaningTranslation
import com.matterox.dictionary.data.entity.WordMeaning

class MeaningMapper {
    fun transform(word: WordMeaning): MeaningModel {
        return MeaningModel(
            word = word.text,
            translation = word.translation.text,
            transcription = word.transcription,
            imageUrl = "https:${word.images.first().url}",
            similarMeaningTranslation = word.similarTranslation
                .filter { it.meaningId.toString() != word.id }
                .map {
                    SimilarMeaningTranslation(
                        word = word.text,
                        meaningId = it.meaningId.toString(),
                        frequencyPercent = it.frequencyPercent,
                        partOfSpeech = it.partOfSpeechAbbreviation,
                        translation = it.translation.text
                    )
                }
        )
    }
}