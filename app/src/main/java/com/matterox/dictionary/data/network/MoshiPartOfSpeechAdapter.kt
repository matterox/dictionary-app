package com.matterox.dictionary.data.network

import com.matterox.dictionary.data.domain.PartOfSpeechCode
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

object MoshiPartOfSpeechAdapter {
    @FromJson
    fun fromJson(string: String): PartOfSpeechCode = PartOfSpeechCode.from(string)

    @ToJson
    fun toJson(value: PartOfSpeechCode) = value.toString()
}