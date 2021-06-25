package com.alvaromr.marvel.ui.detail

import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.ui.BaseViewState

data class MarvelCharacterDetailViewState(
    val marvelCharacter: MarvelCharacter? = null,
) : BaseViewState