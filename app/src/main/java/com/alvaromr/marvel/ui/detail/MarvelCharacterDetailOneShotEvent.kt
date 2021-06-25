package com.alvaromr.marvel.ui.detail

import com.alvaromr.marvel.ui.BaseOneShotEvent

sealed class MarvelCharacterDetailOneShotEvent : BaseOneShotEvent {

    sealed class Error : MarvelCharacterDetailOneShotEvent() {
        object GenericError : Error()
        object MarvelCharacterNotFound : Error()
    }
}