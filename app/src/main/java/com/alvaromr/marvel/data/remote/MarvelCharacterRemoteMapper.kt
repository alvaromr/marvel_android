package com.alvaromr.marvel.data.remote

import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiDto
import com.alvaromr.marvel.model.MarvelCharacter

interface MarvelCharacterRemoteMapper {
    fun toModel(dtoList: List<MarvelCharacterApiDto>): List<MarvelCharacter>
}