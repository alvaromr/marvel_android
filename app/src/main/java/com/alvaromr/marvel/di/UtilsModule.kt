package com.alvaromr.marvel.di

import com.alvaromr.marvel.utils.Logger
import com.alvaromr.marvel.utils.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {
    @Binds
    fun bindLogger(logger: LoggerImpl): Logger
}