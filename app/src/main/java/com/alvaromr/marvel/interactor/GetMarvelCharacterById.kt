package com.alvaromr.marvel.interactor

import com.alvaromr.marvel.model.MarvelCharacter

interface GetMarvelCharacterById {
    suspend operator fun invoke(
        id: String,
        block: suspend (value: Resource<out MarvelCharacter>) -> Unit,
    )

    object MarvelCharacterDetailError : ErrorType
    object MarvelCharacterDetailNotFoundError : ErrorType
}