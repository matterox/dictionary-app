package com.matterox.dictionary.di

import androidx.fragment.app.Fragment
import com.matterox.dictionary.data.domain.mappers.MeaningMapper
import com.matterox.dictionary.data.helpers.PartOfSpeechMapper
import com.matterox.dictionary.ui.meaning.MeaningsViewModel
import com.matterox.dictionary.ui.search.SearchViewModel
import com.matterox.dictionary.ui.search.adapter.SearchedWordAdapter
import com.matterox.dictionary.data.domain.mappers.SearchedWordMapper
import com.matterox.dictionary.ui.meaning.adapter.SimilarMeaningAdapter
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.*

val presentationModule = Kodein.Module("${NAME}PresentationModule") {

    bind<SearchViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelProvider.of(context) {
            SearchViewModel(instance(), instance())
        }
    }

    bind<MeaningsViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelProvider.of(context) {
            MeaningsViewModel(instance(), instance())
        }
    }

    bind<SearchedWordMapper>() with provider { SearchedWordMapper(instance()) }

    bind<MeaningMapper>() with provider { MeaningMapper() }

    bind() from singleton { SearchedWordAdapter() }

    bind() from singleton { SimilarMeaningAdapter() }

    bind() from singleton { PartOfSpeechMapper(instance()) }
}