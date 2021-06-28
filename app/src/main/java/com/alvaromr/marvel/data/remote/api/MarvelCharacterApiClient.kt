package com.alvaromr.marvel.data.remote.api

interface MarvelCharacterApiClient {
    suspend fun get(
        endpoint: String,
        queryParams: Map<String, String> = mapOf(),
    ): List<MarvelCharacterApiDto>
}