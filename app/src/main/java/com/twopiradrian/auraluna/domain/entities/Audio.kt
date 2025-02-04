package com.twopiradrian.auraluna.domain.entities

data class Audio(
    val id: Int,                    // This is the id of the audio in the database
    val name: String,
    val author: String,
    val coverResource: Int,
    val audioResource: Int,         // This is the id of the audio in the audio file
    val type: AudioType,
    val format: AudioFormat,
    val categories: List<AudioCategory>,
    val times: List<Int>,
    val duration: String
)