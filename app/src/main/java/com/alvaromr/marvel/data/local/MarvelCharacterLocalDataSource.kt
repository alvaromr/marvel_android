package com.alvaromr.marvel.data.local

import com.alvaromr.marvel.data.local.db.DatabaseBuilder
import com.alvaromr.marvel.data.local.db.MarvelCharacterDao
import com.alvaromr.marvel.model.MarvelCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterLocalDataSource @Inject constructor(
    private val databaseBuilder: DatabaseBuilder,
    private val marvelCharacterLocalMapper: MarvelCharacterLocalMapper,
) {

    private val marvelCharacterDao: MarvelCharacterDao
        get() = databaseBuilder.db.marvelCharacterDto()

    suspend fun getMarvelCharacterById(id: String): MarvelCharacter? {
        return marvelCharacterLocalMapper.toModel(marvelCharacterDao.findById(id) ?: return null)
    }

    suspend fun saveMarvelCharacters(marvelCharacter: List<MarvelCharacter>) {
        marvelCharacterDao.insert(marvelCharacterLocalMapper.toDto(marvelCharacter))
    }
}