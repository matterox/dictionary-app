package com.matterox.dictionary.ui.meaning

import android.os.Bundle
import android.view.View
import com.matterox.dictionary.R
import com.matterox.dictionary.extentions.observe
import com.matterox.dictionary.ui.base.BaseFragment
import org.kodein.di.generic.instance
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Scale
import com.matterox.dictionary.extentions.showErrorSnackBar
import com.matterox.dictionary.ui.meaning.adapter.SimilarMeaningAdapter
import kotlinx.android.synthetic.main.fragment_meanings.*
import kotlinx.android.synthetic.main.fragment_meanings.toolbar

class MeaningsFragment: BaseFragment(R.layout.fragment_meanings) {

    private val viewModel by instance<MeaningsViewModel>()

    private val adapter by instance<SimilarMeaningAdapter>()

    private val stateObserver = Observer<MeaningsViewModel.ViewState> {
        if (it.isError) view?.showErrorSnackBar(it.errorMessage)
        rvSimilarTranslation.visibility = if (it.inProgress) View.INVISIBLE else View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            MeaningsFragmentArgs.fromBundle(bundle).run {
                viewModel.loadMeaning(
                    meaningId
                )
            }
        }

        observe(viewModel.meaningLiveData, Observer { result ->
            toolbar.title = result.word
            toolbar.subtitle = getString(R.string.searched_word_transcription, result.transcription)
            ivImage.load(result.imageUrl) {
                scale(Scale.FILL)
            }
            tvWord.text = result.word
            tvTranslation.text = result.translation
            tvTranscription.text =
                getString(R.string.searched_word_transcription, result.transcription)
            adapter.items = result.similarMeaningTranslation
        })

        adapter.onDebouncedClickListener = {
            viewModel.onSimilarTranslationClicked(it)
        }

        rvSimilarTranslation.layoutManager = LinearLayoutManager(context)
        rvSimilarTranslation.adapter = adapter

        observe(viewModel.stateLiveData, stateObserver)
    }
}