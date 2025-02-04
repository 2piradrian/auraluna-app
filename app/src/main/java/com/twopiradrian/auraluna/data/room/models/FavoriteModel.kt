package com.twopiradrian.auraluna.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val audioId: Int,

    val name: String,

    val author: String,

)