package com.alvaromr.marvel.data.remote

import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiDto
import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiImageDto
import com.alvaromr.marvel.model.MarvelCharacter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterRemoteMapper @Inject constructor() {
    fun toModel(dtoList: List<MarvelCharacterApiDto>) = dtoList.map { it.toModel() }

    private fun MarvelCharacterApiDto.toModel() = MarvelCharacter(
        id = id.toString(),
        name = name,
        description = description,
        thumbnailUrl = thumbnail.parseUri(),
        modified = modified.parseDate(),
        updated = Date()
    )

    private fun MarvelCharacterApiImageDto.parseUri(): String =
        "${path}/portrait_medium.${extension}"

    private fun String.parseDate(): Date? =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).parse(this)
}

