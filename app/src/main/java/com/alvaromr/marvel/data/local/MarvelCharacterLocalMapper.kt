package com.alvaromr.marvel.data.local

import com.alvaromr.marvel.data.local.db.MarvelCharacterLocalDto
import com.alvaromr.marvel.model.MarvelCharacter

interface MarvelCharacterLocalMapper {
    fun toModel(marvelCharacterLocalDto: MarvelCharacterLocalDto): MarvelCharacter
    fun toDto(list: List<MarvelCharacter>): List<MarvelCharacterLocalDto>
}