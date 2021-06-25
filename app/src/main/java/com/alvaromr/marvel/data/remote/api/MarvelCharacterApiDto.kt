package com.alvaromr.marvel.data.remote.api

import kotlinx.serialization.Serializable

@Serializable
data class MarvelCharacterApiDto(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: MarvelCharacterApiImageDto,
    val modified: String,
)
