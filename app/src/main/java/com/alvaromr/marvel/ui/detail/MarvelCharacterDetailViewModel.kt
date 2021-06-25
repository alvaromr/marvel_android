package com.alvaromr.marvel.ui.detail

import com.alvaromr.marvel.interactor.GetMarvelCharacterById
import com.alvaromr.marvel.interactor.Resource
import com.alvaromr.marvel.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterDetailViewModel @Inject constructor(
    private val getMarvelCharacterById: GetMarvelCharacterById,
) : BaseViewModel<MarvelCharacterDetailViewState, MarvelCharacterDetailOneShotEvent, MarvelCharacterDetailUIAction>() {

    override fun onAction(uiAction: MarvelCharacterDetailUIAction) {
        launchOnMain {
            when (uiAction) {
                is MarvelCharacterDetailUIAction.LoadMarvelCharacterById -> {
                    executeGetMarvelCharacterById(uiAction.id)
                }
            }
        }

    }

    private suspend fun executeGetMarvelCharacterById(id: String) {
        getMarvelCharacterById(id) { resource ->
            when (resource) {
                is Resource.Error -> {
                    updateState {
                        it.copy(loading = false)
                    }
                    when (resource.type) {
                        is GetMarvelCharacterById.MarvelCharacterDetailError ->
                            sendOneShotEvent(MarvelCharacterDetailOneShotEvent.Error.GenericError)
                        is GetMarvelCharacterById.MarvelCharacterDetailNotFoundError ->
                            sendOneShotEvent(MarvelCharacterDetailOneShotEvent.Error.MarvelCharacterNotFound)
                    }
                }
                Resource.Loading -> updateState {
                    it.copy(loading = true)
                }
                is Resource.Success -> updateState {
                    it.copy(
                        loading = false,
                        viewState = viewState.value.viewState.copy(marvelCharacter = resource.data)
                    )
                }
            }
        }
    }

    override fun createInitialViewState() = MarvelCharacterDetailViewState()
}