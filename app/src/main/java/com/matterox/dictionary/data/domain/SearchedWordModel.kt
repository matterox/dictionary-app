package com.matterox.dictionary.data.domain

data class SearchedWordModel(
    val text: String,
    val meaningPreviews: List<MeaningPreviewModel>,
    val meaningsId: List<String>
)