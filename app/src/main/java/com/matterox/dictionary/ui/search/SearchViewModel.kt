package com.matterox.dictionary.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.matterox.dictionary.data.domain.MeaningPreviewModel
import com.matterox.dictionary.data.domain.SearchedWordModel
import com.matterox.dictionary.data.repository.DictionaryRepository
import com.matterox.dictionary.ui.base.BaseAction
import com.matterox.dictionary.ui.base.BaseViewModel
import com.matterox.dictionary.ui.base.BaseViewState
import com.matterox.dictionary.ui.base.navigation.NavManager
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val dictionaryRepository: DictionaryRepository,
    private val navManager: NavManager
): BaseViewModel<SearchViewModel.ViewState, SearchViewModel.Action>(ViewState()) {

    val searchedWordLiveData: MutableLiveData<List<SearchedWordModel>> = MutableLiveData()

    fun searchInput(input: String) {
        viewModelScope.launch {
            sendAction(Action.InProgress)
            dictionaryRepository.searchWord(input).fold(
                success = {
                    sendAction(Action.Success)
                    searchedWordLiveData.value = it
                },
                failure = { sendAction(Action.Error(it)) }
            )
        }
    }

    fun onMeaningClicked(meaningPreview: MeaningPreviewModel) {
        navManager.navigate(
            SearchFragmentDirections.actionSearchFragmentToMeaningsFragment(
                meaningId = meaningPreview.meaningId
            )
        )
    }

    internal data class ViewState(
        val inProgress: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object InProgress : Action()
        object Success : Action()
        data class Error(val errorMessage: String) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        is Action.InProgress -> state.copy(
            isError = false,
            inProgress = true,
            errorMessage = ""
        )
        is Action.Success -> state.copy(
            isError = false,
            inProgress = false,
            errorMessage = ""
        )
        is Action.Error -> state.copy(
            inProgress = false,
            isError = true,
            errorMessage = viewAction.errorMessage
        )
    }
}