package com.alvaromr.marvel.data.remote.api

import kotlinx.serialization.Serializable

@Serializable
data class MarvelCharacterApiImageDto(
    val path: String,
    val extension: String,
)