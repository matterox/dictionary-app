package com.matterox.dictionary.data.network.dictionaryapi

import com.matterox.dictionary.data.entity.Word
import com.matterox.dictionary.data.entity.WordMeaning
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    companion object {
        private const val PREFIX = "api/public/v1"
    }

    @GET("$PREFIX/words/search")
    suspend fun searchWord(
        @Query("search") query: String,
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 10
    ): Response<List<Word>>

    @GET("$PREFIX/meanings")
    suspend fun getMeanings(
        @Query("ids") meaningIds: List<String>
    ): Response<List<WordMeaning>>
}