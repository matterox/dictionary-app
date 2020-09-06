package com.matterox.dictionary.data.repository

import com.matterox.dictionary.data.domain.MeaningModel
import com.matterox.dictionary.data.domain.SearchedWordModel
import com.matterox.dictionary.data.domain.mappers.MeaningMapper
import com.matterox.dictionary.data.network.RequestHandler
import com.matterox.dictionary.data.network.Result
import com.matterox.dictionary.data.network.dictionaryapi.DictionaryApi
import com.matterox.dictionary.data.domain.mappers.SearchedWordMapper

class DictionaryRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
    private val searchedWordMapper: SearchedWordMapper,
    private val meaningMapper: MeaningMapper,
    private val requestHandler: RequestHandler
): DictionaryRepository {
    override suspend fun searchWord(input: String): Result<String, List<SearchedWordModel>> {
        return requestHandler.safeRequest(
            response = { dictionaryApi.searchWord(input) },
            successTransform = { data -> data.map { searchedWordMapper.transform(it)  } }
        )
    }

    override suspend fun getMeanings(meaningIds: List<String>): Result<String, List<MeaningModel>> {
        return requestHandler.safeRequest(
            response = { dictionaryApi.getMeanings(meaningIds) },
            successTransform = { data -> data.map { meaningMapper.transform(it) } }
        )
    }
}