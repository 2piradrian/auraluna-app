package com.twopiradrian.auraluna.core.container

import android.content.Context
import com.twopiradrian.auraluna.core.utils.ResourceUtils
import com.twopiradrian.auraluna.data.json.mapper.AudioMapper
import com.twopiradrian.auraluna.data.room.database.FavoritesDatabase
import com.twopiradrian.auraluna.data.room.mappers.FavoritesMapper
import com.twopiradrian.auraluna.infrastructure.datasources.json.JsonAudioDatasource
import com.twopiradrian.auraluna.infrastructure.repositories.AudiosRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoritesRepository

class AppContainer(
    private val context: Context
) {

    val favoritesRepository by lazy {
        FavoritesRepository(
            favorites = FavoritesDatabase.getDatabase(context).datasource(),
            mapper = FavoritesMapper()
        )
    }

    val audiosRepository by lazy {
        AudiosRepository(
            audios = JsonAudioDatasource(context),
            mapper = AudioMapper(ResourceUtils(context))
        )
    }

}