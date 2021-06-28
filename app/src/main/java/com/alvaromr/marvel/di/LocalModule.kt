package com.alvaromr.marvel.di

import android.content.Context
import androidx.room.Room
import com.alvaromr.marvel.data.local.MarvelCharacterLocalDataSource
import com.alvaromr.marvel.data.local.MarvelCharacterLocalDataSourceImpl
import com.alvaromr.marvel.data.local.MarvelCharacterLocalMapper
import com.alvaromr.marvel.data.local.MarvelCharacterLocalMapperImpl
import com.alvaromr.marvel.data.local.db.AppDatabase
import com.alvaromr.marvel.data.local.db.MarvelCharacterDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalModule {
    @Binds
    fun bindMarvelCharacterLocalDataSource(ds: MarvelCharacterLocalDataSourceImpl): MarvelCharacterLocalDataSource

    @Binds
    fun bindMarvelCharacterLocalMapper(mapper: MarvelCharacterLocalMapperImpl): MarvelCharacterLocalMapper

    companion object {
        @Provides
        fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "com.alvaromr.marvel"
            ).build()

        @Provides
        fun providesMarvelCharacterDao(db: AppDatabase): MarvelCharacterDao =
            db.marvelCharacterDao()
    }
}