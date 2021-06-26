package com.alvaromr.marvel.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelCharacterDao {
    @Query("SELECT * FROM character WHERE id LIKE :id")
    suspend fun findById(id: String): MarvelCharacterLocalDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dto: List<MarvelCharacterLocalDto>)
}