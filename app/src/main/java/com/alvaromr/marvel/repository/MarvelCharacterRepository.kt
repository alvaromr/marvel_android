package com.alvaromr.marvel.repository

import com.alvaromr.marvel.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelCharacterRepository {
    suspend fun getMarvelCharacters(offset: Int): Flow<List<MarvelCharacter>>
    suspend fun getMarvelCharacterById(id: String): Flow<MarvelCharacter?>
}