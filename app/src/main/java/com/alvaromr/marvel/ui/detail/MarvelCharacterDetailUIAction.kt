package com.alvaromr.marvel.ui.detail

import com.alvaromr.marvel.ui.BaseUIAction

sealed class MarvelCharacterDetailUIAction : BaseUIAction {
    class LoadMarvelCharacterById(
        val id: String,
    ) : MarvelCharacterDetailUIAction()
}