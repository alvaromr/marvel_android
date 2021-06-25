package com.alvaromr.marvel.data.remote.api

import kotlinx.serialization.Serializable

@Serializable
class MarvelCharacterApiResponse(
    val data: MarvelCharacterApiData,
)