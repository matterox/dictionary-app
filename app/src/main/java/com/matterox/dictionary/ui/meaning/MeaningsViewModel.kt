package com.matterox.dictionary.ui.meaning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.matterox.dictionary.data.domain.MeaningModel
import com.matterox.dictionary.data.domain.SimilarMeaningTranslation
import com.matterox.dictionary.data.repository.DictionaryRepository
import com.matterox.dictionary.ui.base.BaseAction
import com.matterox.dictionary.ui.base.BaseViewModel
import com.matterox.dictionary.ui.base.BaseViewState
import com.matterox.dictionary.ui.base.navigation.NavManager
import kotlinx.coroutines.launch
import timber.log.Timber

internal class MeaningsViewModel(
    private val dictionaryRepository: DictionaryRepository,
    private val navManager: NavManager
): BaseViewModel<MeaningsViewModel.ViewState, MeaningsViewModel.Action>(ViewState()) {

    val meaningLiveData: MutableLiveData<MeaningModel> = MutableLiveData()

    fun loadMeaning(meaningId: String) {
        sendAction(Action.InProgress)
        viewModelScope.launch {
            dictionaryRepository.getMeanings(listOf(meaningId)).fold(
                success = {
                    sendAction(Action.Success)
                    meaningLiveData.value = it.first()
                },
                failure = {
                    Timber.e(it)
                    sendAction(Action.Error(it))
                }
            )
        }
    }

    fun onSimilarTranslationClicked(similarMeaningTranslation: SimilarMeaningTranslation) {
        navManager.navigate(
            MeaningsFragmentDirections.actionMeaningsFragmentToMeaningsFragment(
                meaningId = similarMeaningTranslation.meaningId
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