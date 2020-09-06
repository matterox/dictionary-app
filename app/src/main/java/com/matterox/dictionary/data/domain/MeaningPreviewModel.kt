package com.matterox.dictionary.data.domain

data class MeaningPreviewModel(
    val meaningId: String,
    val translation: String,
    val translationNote: String?,
    val transcription: String,
    val previewUrl: String,
    val partOfSpeech: String?
)