package com.alvaromr.marvel.di

import com.alvaromr.marvel.interactor.GetMarvelCharacterById
import com.alvaromr.marvel.interactor.GetMarvelCharacterByIdImpl
import com.alvaromr.marvel.interactor.GetMarvelCharacterList
import com.alvaromr.marvel.interactor.GetMarvelCharacterListImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    fun bindGetMarvelCharacterById(uc: GetMarvelCharacterByIdImpl): GetMarvelCharacterById

    @Binds
    fun bindGetMarvelCharacterList(uc: GetMarvelCharacterListImpl): GetMarvelCharacterList
}