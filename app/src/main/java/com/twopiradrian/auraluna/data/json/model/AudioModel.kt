package com.twopiradrian.auraluna.data.json.model

data class AudioModel(
    val id: Int,
    val name: String,
    val author: String,
    val coverResource: String,
    val audioResource: String,
    val type: String,
    val format: String,
    val categories: List<String>,
    val times: List<Int>,
    val duration: String
)