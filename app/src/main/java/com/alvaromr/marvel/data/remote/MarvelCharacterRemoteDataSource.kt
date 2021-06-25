package com.alvaromr.marvel.data.remote

import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterRemoteDataSource @Inject constructor(
    private val marvelCharacterApiClient: MarvelCharacterApiClient,
    private val marvelCharacterRemoteMapper: MarvelCharacterRemoteMapper,
) {
    suspend fun getMarvelCharacters(offset: Int) =
        marvelCharacterRemoteMapper.toModel(
            marvelCharacterApiClient.get(
                "characters", mapOf(
                    "offset" to offset.toString(),
                    "limit" to LIMIT.toString()
                )
            )
        )

    suspend fun getMarvelCharacterById(id: String) =
        marvelCharacterRemoteMapper.toModel(
            marvelCharacterApiClient.get("characters/$id")
        ).firstOrNull()

    companion object {
        const val LIMIT = 20
    }
}