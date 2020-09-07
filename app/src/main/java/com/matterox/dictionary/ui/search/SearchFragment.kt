package com.matterox.dictionary.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.matterox.dictionary.R
import com.matterox.dictionary.extentions.*
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

        val searchView = toolbar.menu.findItem(R.id.menu_search_bar).actionView as SearchView
        searchView.onQueryTextChange{
            if (it.isNotBlank()) onSearchChanged(it)
        }

        observe(viewModel.searchedWordLiveData, Observer { result ->
            adapter.parents = result
        })

        adapter.onDebouncedClickListener = {
            searchView.hideKeyboard()
            viewModel.onMeaningClicked(it)
        }
        rvWords.layoutManager = LinearLayoutManager(context)
        rvWords.adapter = adapter

        observe(viewModel.stateLiveData, stateObserver)
    }
}