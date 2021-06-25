package com.alvaromr.marvel.repository

import com.alvaromr.marvel.data.remote.MarvelCharacterRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterRepository @Inject constructor(
    private val marvelCharacterRemoteDataSource: MarvelCharacterRemoteDataSource,
) {
    suspend fun getMarvelCharacters(offset: Int) = flow {
        emit(marvelCharacterRemoteDataSource.getMarvelCharacters(offset))
    }

    fun getMarvelCharacterById(id: String) = flow {
        emit(marvelCharacterRemoteDataSource.getMarvelCharacterById(id))
    }
}