package com.alvaromr.marvel.interactor

import com.alvaromr.marvel.model.MarvelCharacter

interface GetMarvelCharacterList {
    suspend operator fun invoke(
        offset: Int,
        block: suspend (value: Resource<out List<MarvelCharacter>>) -> Unit,
    )

    object MarvelCharacterListError : ErrorType
}