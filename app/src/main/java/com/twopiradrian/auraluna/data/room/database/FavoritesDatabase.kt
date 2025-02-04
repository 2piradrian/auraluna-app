package com.twopiradrian.auraluna.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.twopiradrian.auraluna.data.room.models.FavoriteModel
import com.twopiradrian.auraluna.infrastructure.datasources.room.RoomFavoritesDatasource

@Database(
    entities = [FavoriteModel::class],
    version = 1,
    exportSchema = false
)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun datasource(): RoomFavoritesDatasource

    companion object {

        @Volatile
        private var Instance: FavoritesDatabase? = null

        fun getDatabase(context: Context): FavoritesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FavoritesDatabase::class.java, "favorites_database")
                .fallbackToDestructiveMigration()
                .build()
                .also { Instance = it }
            }
        }

    }

}