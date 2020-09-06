package com.matterox.dictionary.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

abstract class BaseViewModel<ViewState: BaseViewState, ViewAction: BaseAction>(initialState: ViewState):
    ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()

    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    protected var state by Delegates.observable(initialState) { _, _, new ->
        stateMutableLiveData.value = new
    }

    fun sendAction(viewAction: ViewAction) {
        state = onReduceState(viewAction)
    }

    fun loadData() {
        onLoadData()
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}