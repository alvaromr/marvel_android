package com.alvaromr.marvel.di

import com.alvaromr.marvel.data.remote.MarvelCharacterRemoteDataSource
import com.alvaromr.marvel.data.remote.MarvelCharacterRemoteDataSourceImpl
import com.alvaromr.marvel.data.remote.MarvelCharacterRemoteMapper
import com.alvaromr.marvel.data.remote.MarvelCharacterRemoteMapperImpl
import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiClient
import com.alvaromr.marvel.data.remote.api.MarvelCharacterApiClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {
    @Binds
    fun bindMarvelCharacterApiClient(apiClient: MarvelCharacterApiClientImpl): MarvelCharacterApiClient

    @Binds
    fun bindMarvelCharacterRemoteMapper(mapper: MarvelCharacterRemoteMapperImpl): MarvelCharacterRemoteMapper

    @Binds
    fun bindMarvelCharacterRemoteDataSource(ds: MarvelCharacterRemoteDataSourceImpl): MarvelCharacterRemoteDataSource
}