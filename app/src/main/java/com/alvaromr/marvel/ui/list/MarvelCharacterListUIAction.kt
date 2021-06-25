package com.alvaromr.marvel.ui.list

import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.ui.BaseUIAction

sealed class MarvelCharacterListUIAction : BaseUIAction {
    class MarvelCharacterSelected(
        val marvelCharacter: MarvelCharacter,
    ) : MarvelCharacterListUIAction()

    class ListScrolledToEndPosition(
        val position: Int,
    ) : MarvelCharacterListUIAction()
}