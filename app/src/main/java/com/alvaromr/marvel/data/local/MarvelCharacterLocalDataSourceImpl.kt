package com.alvaromr.marvel.data.local

import com.alvaromr.marvel.data.local.db.MarvelCharacterDao
import com.alvaromr.marvel.model.MarvelCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterLocalDataSourceImpl @Inject constructor(
    private val marvelCharacterDao: MarvelCharacterDao,
    private val marvelCharacterLocalMapper: MarvelCharacterLocalMapper,
) : MarvelCharacterLocalDataSource {

    override suspend fun saveMarvelCharacters(marvelCharacter: List<MarvelCharacter>) {
        marvelCharacterDao.insert(marvelCharacterLocalMapper.toDto(marvelCharacter))
    }

    override suspend fun getMarvelCharacterById(id: String): MarvelCharacter? {
        return marvelCharacterLocalMapper.toModel(marvelCharacterDao.findById(id) ?: return null)
    }
}