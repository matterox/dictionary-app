package com.matterox.dictionary.data.domain

import com.squareup.moshi.Json

enum class PartOfSpeechCode(val code: String? = null) {
    UNKNOWN,
    @Json(name = "n") NOUN("n"),
    @Json(name = "v") VERB("v"),
    @Json(name = "j") ADJECTIVE("j"),
    @Json(name = "t") ADVERB("r"),
    @Json(name = "prp") PREPOSITION("prp"),
    @Json(name = "prn") PRONOUN("prn"),
    @Json(name = "crd") CARDINAL_NUMBER("crd"),
    @Json(name = "cjc") CONJUNCTION("cjc"),
    @Json(name = "exc") INTERJECTION("exc"),
    @Json(name = "det") ARTICLE("det"),
    @Json(name = "abb") ABBREVIATION("abb"),
    @Json(name = "x") PARTICLE("x"),
    @Json(name = "ord") ORDINAL_NUMBER("ord"),
    @Json(name = "md") MODAL_VERB("md"),
    @Json(name = "ph") PHRASE("ph"),
    @Json(name = "phi") IDIOM("phi");

    companion object {
        fun from(s: String?): PartOfSpeechCode = values().firstOrNull { it.code == s } ?: UNKNOWN
    }
}