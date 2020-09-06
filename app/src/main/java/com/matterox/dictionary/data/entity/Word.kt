package com.matterox.dictionary.data.entity

data class Word(
    val id: Int,
    val meanings: List<Meaning>,
    val text: String
)