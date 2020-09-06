package com.matterox.dictionary.di

import com.matterox.dictionary.data.network.dictionaryapi.DictionaryApi
import com.matterox.dictionary.data.repository.DictionaryRepositoryImpl
import com.matterox.dictionary.data.repository.DictionaryRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${NAME}DataModule") {
    bind<DictionaryApi>() with provider {
        instance<Retrofit>().create(DictionaryApi::class.java)
    }

    bind<DictionaryRepository>() with singleton {
        DictionaryRepositoryImpl(
            instance(),
            instance(),
            instance(),
            instance()
        )
    }
}