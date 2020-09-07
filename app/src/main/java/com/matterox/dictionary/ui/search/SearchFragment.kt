package com.matterox.dictionary.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.matterox.dictionary.R
import com.matterox.dictionary.extentions.hideKeyboard
import com.matterox.dictionary.extentions.observe
import com.matterox.dictionary.extentions.showErrorSnackBar
import com.matterox.dictionary.extentions.throttleLatest
import com.matterox.dictionary.ui.base.BaseFragment
import com.matterox.dictionary.ui.search.adapter.SearchedWordAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.kodein.di.generic.instance

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    private val viewModel by instance<SearchViewModel>()
    private val adapter by instance<SearchedWordAdapter>()

    private val stateObserver = Observer<SearchViewModel.ViewState> {
        if (it.isError) view?.showErrorSnackBar(it.errorMessage)
        progressBarHolder.isVisible = it.inProgress
        rvWords.visibility = if (it.inProgress) View.INVISIBLE else View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.main_search_title)

        val onSearchChanged: (String) -> Unit =
            throttleLatest(scope = viewModel.viewModelScope) { viewModel.searchInput(it) }
        tiWordSearch.doOnTextChanged { text, _, _, _ ->
            onSearchChanged(text.toString())
        }

        observe(viewModel.searchedWordLiveData, Observer { result ->
            adapter.parents = result
        })

        adapter.onDebouncedClickListener = {
            tiWordSearch.hideKeyboard()
            viewModel.onMeaningClicked(it)
        }
        rvWords.layoutManager = LinearLayoutManager(context)
        rvWords.adapter = adapter

        observe(viewModel.stateLiveData, stateObserver)
    }
}