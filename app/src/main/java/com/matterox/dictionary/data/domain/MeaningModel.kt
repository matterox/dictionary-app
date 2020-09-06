package com.matterox.dictionary.data.domain

data class MeaningModel(
    val word: String,
    val translation: String,
    val transcription: String,
    val imageUrl: String,
    val similarMeaningTranslation: List<SimilarMeaningTranslation>
)