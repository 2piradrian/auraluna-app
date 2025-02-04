package com.twopiradrian.auraluna.data.room.mappers

import com.twopiradrian.auraluna.data.room.models.FavoriteModel
import com.twopiradrian.auraluna.domain.entities.Favorite

class FavoritesMapper {

    fun toDomain(model: FavoriteModel): Favorite {
        return Favorite(
            id = model.id,
            audioId = model.audioId,
            name = model.name,
            author = model.author
        )
    }

    fun toModel(domain: Favorite): FavoriteModel {
        return FavoriteModel(
            id = domain.id,
            audioId = domain.audioId,
            name = domain.name,
            author = domain.author
        )
    }

}