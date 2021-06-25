package com.alvaromr.marvel.model

import java.io.Serializable
import java.util.*

data class MarvelCharacter(
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val modified: Date?,
    val updated: Date?,
) : Serializable