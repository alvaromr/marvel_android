package com.alvaromr.marvel.data.remote.api

import com.alvaromr.marvel.DebugTools
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpClientBuilder @Inject constructor(
    private val debugTools: DebugTools,
) {

    fun httpClient() = HttpClient(OkHttp) {
        engine {
            debugTools.httpInterceptor?.let {
                addInterceptor(it)
            }
        }
        install(JsonFeature) {
            serializer =
                KotlinxSerializer(kotlinx.serialization.json.Json { ignoreUnknownKeys = true })
        }
    }
}