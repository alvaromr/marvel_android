package com.alvaromr.marvel.ui.list

import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.ui.BaseOneShotEvent

sealed class MarvelCharacterListOneShotEvent : BaseOneShotEvent {
    class NavigateToMarvelCharacterDetail(val marvelCharacter: MarvelCharacter) :
        MarvelCharacterListOneShotEvent()

    sealed class Error : MarvelCharacterListOneShotEvent() {
        object MarvelCharacterList : Error()
    }
}