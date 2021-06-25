package com.alvaromr.marvel.data.remote.api

import com.alvaromr.marvel.di.AppModule.Companion.API_KEY
import com.alvaromr.marvel.di.AppModule.Companion.BASE_URL
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MarvelCharacterApiClient @Inject constructor(
    private val httpClientBuilder: HttpClientBuilder,
    @Named(API_KEY) private val apiKey: String,
    @Named(BASE_URL) private val baseUrl: String,
) {
    suspend fun get(
        endpoint: String,
        queryParams: Map<String, String> = mapOf(),
    ): List<MarvelCharacterApiDto> {
        val client = httpClientBuilder.httpClient()
        val response: MarvelCharacterApiResponse = client.get("$baseUrl$endpoint") {
            headers {
                append(HttpHeaders.Referrer, REFERER_URL) // to avoid sending hash to marvel api
            }
            parameter("apikey", apiKey)
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
        return response.data.results
    }

    companion object {
        const val REFERER_URL = "https://developer.marvel.com"
    }
}

