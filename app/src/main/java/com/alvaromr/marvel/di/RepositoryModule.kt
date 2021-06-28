package com.alvaromr.marvel.di

import com.alvaromr.marvel.repository.MarvelCharacterRepository
import com.alvaromr.marvel.repository.MarvelCharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMarvelCharacterApiClient(repo: MarvelCharacterRepositoryImpl): MarvelCharacterRepository
}