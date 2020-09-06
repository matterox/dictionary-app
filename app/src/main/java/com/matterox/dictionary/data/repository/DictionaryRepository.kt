package com.matterox.dictionary.data.repository

import com.matterox.dictionary.data.domain.MeaningModel
import com.matterox.dictionary.data.domain.SearchedWordModel
import com.matterox.dictionary.data.network.Result

interface DictionaryRepository {
    suspend fun searchWord(input: String): Result<String, List<SearchedWordModel>>
    suspend fun getMeanings(meaningIds: List<String>): Result<String, List<MeaningModel>>
}