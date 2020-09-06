package com.matterox.dictionary.data.entity

import com.squareup.moshi.Json

data class WordMeaning(
    val alternativeTranslations: List<AlternativeTranslation>,
    val definition: Definition,
    val difficultyLevel: Int?,
    val examples: List<Example>,
    val id: String,
    val images: List<Image>,
    @field:Json(name = "meaningsWithSimilarTranslation")
    val similarTranslation: List<SimilarTranslation>,
    val mnemonics: String,
    val partOfSpeechCode: String,
    val prefix: String,
    val properties: Properties,
    val soundUrl: String,
    val text: String,
    val transcription: String,
    val translation: Translation,
    val updatedAt: String,
    val wordId: Int
)