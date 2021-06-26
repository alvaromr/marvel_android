package com.alvaromr.marvel.data.local.db

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseBuilder @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "com.alvaromr.marvel"
    ).build()
}