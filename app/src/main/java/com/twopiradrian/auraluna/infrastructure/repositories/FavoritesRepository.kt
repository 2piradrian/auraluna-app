package com.twopiradrian.auraluna.infrastructure.repositories

import com.twopiradrian.auraluna.data.room.mappers.FavoritesMapper
import com.twopiradrian.auraluna.data.room.models.FavoriteModel
import com.twopiradrian.auraluna.domain.entities.Favorite
import com.twopiradrian.auraluna.domain.repositories.FavoritesRepositoryI
import com.twopiradrian.auraluna.infrastructure.datasources.room.RoomFavoritesDatasource

class FavoritesRepository(
    private val favorites: RoomFavoritesDatasource,
    private val mapper: FavoritesMapper
): FavoritesRepositoryI {

    override suspend fun insert(favorite: Favorite) {
        try {
            val model: FavoriteModel = mapper.toModel(favorite)
            this.favorites.insert(model)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getAll(): List<Favorite> {
        try {
            val models: List<FavoriteModel> = this.favorites.getAll()
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    override suspend fun getById(audioId: Int): Favorite? {
        try {
            val model: FavoriteModel = this.favorites.getById(audioId)
            return mapper.toDomain(model)
        }
        catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun delete(favorite: Favorite) {
        try {
            val model: FavoriteModel = mapper.toModel(favorite)
            this.favorites.delete(model)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

}