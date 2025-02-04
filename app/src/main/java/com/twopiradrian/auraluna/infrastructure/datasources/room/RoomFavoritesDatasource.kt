package com.twopiradrian.auraluna.infrastructure.datasources.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twopiradrian.auraluna.data.room.models.FavoriteModel

@Dao
interface RoomFavoritesDatasource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteModel)

    @Query("SELECT * FROM favorites ORDER BY audioId ASC")
    suspend fun getAll(): List<FavoriteModel>

    @Query("SELECT * from favorites WHERE audioId = :audioId")
    suspend fun getById(audioId: Int): FavoriteModel

    @Delete
    suspend fun delete(favorite: FavoriteModel)

}