package com.matterox.dictionary.data.entity

data class SimilarTranslation(
    val frequencyPercent: String,
    val meaningId: Int,
    val partOfSpeechAbbreviation: String,
    val translation: Translation
)