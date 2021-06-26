package com.alvaromr.marvel.repository

import com.alvaromr.marvel.data.local.MarvelCharacterLocalDataSource
import com.alvaromr.marvel.data.remote.MarvelCharacterRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterRepository @Inject constructor(
    private val marvelCharacterRemoteDataSource: MarvelCharacterRemoteDataSource,
    private val marvelCharacterLocalDataSource: MarvelCharacterLocalDataSource,
) {
    suspend fun getMarvelCharacters(offset: Int) = flow {
        val list = marvelCharacterRemoteDataSource.getMarvelCharacters(offset)
        marvelCharacterLocalDataSource.saveMarvelCharacters(list)
        emit(list)
    }

    fun getMarvelCharacterById(id: String) = flow {
        marvelCharacterLocalDataSource.getMarvelCharacterById(id)?.let {
            emit(it)
        }
        val marvelCharacter = marvelCharacterRemoteDataSource.getMarvelCharacterById(id)
        marvelCharacter?.let {
            marvelCharacterLocalDataSource.saveMarvelCharacters(listOf(it))
        }
        emit(marvelCharacter)
    }
}