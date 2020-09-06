package com.matterox.dictionary.data.entity

import com.matterox.dictionary.data.domain.PartOfSpeechCode

data class Meaning(
    val id: String,
    val imageUrl: String,
    val partOfSpeechCode: PartOfSpeechCode,
    val previewUrl: String,
    val soundUrl: String,
    val transcription: String,
    val translation: Translation
)