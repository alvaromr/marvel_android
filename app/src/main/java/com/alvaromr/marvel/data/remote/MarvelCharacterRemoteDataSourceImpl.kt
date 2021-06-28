package com.alvaromr.marvel.data.remote

import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCharacterRemoteDataSourceImpl @Inject constructor(
    private val marvelCharacterApiClient: MarvelCharacterApiClient,
    private val marvelCharacterRemoteMapper: MarvelCharacterRemoteMapper,
) : MarvelCharacterRemoteDataSource {
    override suspend fun getMarvelCharacters(offset: Int) =
        marvelCharacterRemoteMapper.toModel(
            marvelCharacterApiClient.get(
                "characters", mapOf(
                    "offset" to offset.toString(),
                    "limit" to LIMIT.toString()
                )
            )
        )

    override suspend fun getMarvelCharacterById(id: String) =
        marvelCharacterRemoteMapper.toModel(
            marvelCharacterApiClient.get("characters/$id")
        ).firstOrNull()

    companion object {
        const val LIMIT = 20
    }
}