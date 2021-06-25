package com.alvaromr.marvel.ui.list

import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.ui.BaseViewState

data class MarvelCharacterListViewState(
    val marvelCharacterList: List<MarvelCharacter> = listOf(),
    val maxScroll: Int = 0,
) : BaseViewState