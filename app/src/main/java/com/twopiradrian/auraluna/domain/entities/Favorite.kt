package com.twopiradrian.auraluna.domain.entities

data class Favorite(
    val id: Int = 0,    // This is the id of the favorite in the favorites database
    val audioId: Int,   // This is the id of the audio in the audios database (FK)
    val name: String,
    val author: String,
)