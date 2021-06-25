package com.alvaromr.marvel.di

import com.alvaromr.marvel.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Named(API_KEY)
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    companion object {
        const val API_KEY = "API_KEY"
        const val BASE_URL = "BASE_URL"
    }
}