package com.alvaromr.marvel.data.remote

import com.alvaromr.marvel.model.MarvelCharacter

interface MarvelCharacterRemoteDataSource {
    suspend fun getMarvelCharacters(offset: Int): List<MarvelCharacter>
    suspend fun getMarvelCharacterById(id: String): MarvelCharacter?
}