package com.alvaromr.marvel.data.local

import com.alvaromr.marvel.model.MarvelCharacter

interface MarvelCharacterLocalDataSource {

    suspend fun saveMarvelCharacters(marvelCharacter: List<MarvelCharacter>)
    suspend fun getMarvelCharacterById(id: String): MarvelCharacter?
}