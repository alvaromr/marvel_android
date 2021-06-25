package com.alvaromr.marvel.data.remote.api

import kotlinx.serialization.Serializable

@Serializable
data class MarvelCharacterApiData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<MarvelCharacterApiDto>,
)