package com.alvaromr.marvel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S : BaseViewState, E : BaseOneShotEvent, A : BaseUIAction> :
    ViewModel() {
    private val coroutineScope = MainScope()

    private val _viewState: MutableStateFlow<DataState<S>> by lazy {
        MutableStateFlow(DataState(
            viewState = createInitialViewState()
        ))
    }

    val viewState = _viewState.asStateFlow()

    private val _oneShotEvents = Channel<E>(Channel.BUFFERED)

    val oneShotEvents = _oneShotEvents.receiveAsFlow()

    abstract fun createInitialViewState(): S

    abstract fun onAction(uiAction: A)

    protected fun launchOnMain(block: suspend CoroutineScope.() -> Unit) =
        coroutineScope.launch(block = block)

    protected suspend fun launchOnIO(block: suspend CoroutineScope.() -> Unit) =
        withContext(Dispatchers.IO, block = block)

    protected fun updateState(block: (DataState<S>) -> DataState<S>) {
        _viewState.value = block.invoke(_viewState.value)
    }

    protected suspend fun sendOneShotEvent(event: E) {
        _oneShotEvents.send(event)
    }
}

