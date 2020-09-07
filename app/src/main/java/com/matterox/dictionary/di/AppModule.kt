package com.matterox.dictionary.di

import com.matterox.dictionary.BuildConfig
import com.matterox.dictionary.data.network.MoshiPartOfSpeechAdapter
import com.matterox.dictionary.data.network.RequestHandler
import com.matterox.dictionary.data.network.common.ApiEndpoints
import com.matterox.dictionary.ui.base.navigation.NavManager
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal const val NAME = "App"

val appModule = Kodein.Module("${NAME}Module") {
    bind<Moshi>() with singleton { Moshi.Builder().add(MoshiPartOfSpeechAdapter).build() }

    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }
    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(ApiEndpoints.DICTIONARY_API)
            .addConverterFactory(MoshiConverterFactory.create(instance()))
            .client(instance())
            .build()
    }

    bind<RequestHandler>() with provider { RequestHandler(instance()) }

    bind() from singleton { NavManager() }
}