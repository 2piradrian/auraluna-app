package com.twopiradrian.auraluna.domain.repositories

import com.twopiradrian.auraluna.domain.entities.Favorite

interface FavoritesRepositoryI {

    suspend fun insert(favorite: Favorite)

    suspend fun getAll(): List<Favorite> // can return empty list

    suspend fun getById(audioId: Int): Favorite?

    suspend fun delete(favorite: Favorite)

}