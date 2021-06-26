package com.alvaromr.marvel.data.local

import com.alvaromr.marvel.data.local.db.MarvelCharacterLocalDto
import com.alvaromr.marvel.model.MarvelCharacter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterLocalMapper @Inject constructor() {
    fun toModel(marvelCharacterLocalDto: MarvelCharacterLocalDto) = with(marvelCharacterLocalDto) {
        MarvelCharacter(
            id = id,
            name = name,
            description = description,
            thumbnailUrl = thumbnailUrl,
            modified = modified,
            updated = updated
        )
    }

    private fun MarvelCharacter.toDto() =
        MarvelCharacterLocalDto(
            id = id,
            name = name,
            description = description,
            thumbnailUrl = thumbnailUrl,
            modified = modified,
            updated = updated
        )


    fun toDto(list: List<MarvelCharacter>) = list.map { it.toDto() }
}

