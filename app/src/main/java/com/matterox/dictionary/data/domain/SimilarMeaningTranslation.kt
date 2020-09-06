package com.matterox.dictionary.data.domain

data class SimilarMeaningTranslation(
    val word: String,
    val meaningId: String,
    val frequencyPercent: String,
    val partOfSpeech: String?,
    val translation: String
)