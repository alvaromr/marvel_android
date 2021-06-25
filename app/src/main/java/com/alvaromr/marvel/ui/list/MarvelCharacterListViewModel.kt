package com.alvaromr.marvel.ui.list

import com.alvaromr.marvel.interactor.GetMarvelCharacterList
import com.alvaromr.marvel.interactor.Resource
import com.alvaromr.marvel.ui.BaseViewModel
import com.alvaromr.marvel.ui.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterListViewModel @Inject constructor(
    private val getMarvelCharacterList: GetMarvelCharacterList,
) : BaseViewModel<MarvelCharacterListViewState, MarvelCharacterListOneShotEvent, MarvelCharacterListUIAction>() {

    init {
        launchOnMain {
            launchOnIO {
                executeGetMarvelCharacters()
            }
        }
    }

    override fun onAction(uiAction: MarvelCharacterListUIAction) {
        launchOnMain {
            when (uiAction) {
                is MarvelCharacterListUIAction.MarvelCharacterSelected -> {
                    sendOneShotEvent(
                        MarvelCharacterListOneShotEvent.NavigateToMarvelCharacterDetail(
                            uiAction.marvelCharacter
                        )
                    )
                }
                is MarvelCharacterListUIAction.ListScrolledToEndPosition -> {
                    val position = uiAction.position
                    val dataState: DataState<MarvelCharacterListViewState> = viewState.value
                    if (!dataState.loading && position >= dataState.viewState.marvelCharacterList.lastIndex &&
                        position >= dataState.viewState.maxScroll
                    ) {
                        val maxScroll = position + 1
                        updateState {
                            it.copy(
                                viewState = dataState.viewState.copy(
                                    maxScroll = maxScroll
                                )
                            )
                        }
                        executeGetMarvelCharacters(maxScroll)
                    }
                }
            }
        }
    }

    private suspend fun executeGetMarvelCharacters(offset: Int = 0) {
        getMarvelCharacterList(offset) { resource ->
            when (resource) {
                is Resource.Error -> {
                    updateState {
                        it.copy(loading = false)
                    }
                    when (resource.type) {
                        is GetMarvelCharacterList.MarvelCharacterListError ->
                            sendOneShotEvent(MarvelCharacterListOneShotEvent.Error.MarvelCharacterList)
                    }
                }
                Resource.Loading -> updateState {
                    it.copy(loading = true)
                }
                is Resource.Success -> updateState {
                    val value = viewState.value.viewState.marvelCharacterList
                    val list = value.toMutableList().apply {
                        addAll(resource.data)
                    }
                    it.copy(
                        loading = false,
                        viewState = viewState.value.viewState.copy(marvelCharacterList = list)
                    )
                }
            }
        }
    }

    override fun createInitialViewState() =
        MarvelCharacterListViewState(marvelCharacterList = listOf())
}