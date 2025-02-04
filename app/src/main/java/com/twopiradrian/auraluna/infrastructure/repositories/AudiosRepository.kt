package com.twopiradrian.auraluna.infrastructure.repositories

import com.twopiradrian.auraluna.data.json.mapper.AudioMapper
import com.twopiradrian.auraluna.data.json.model.AudioModel
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.domain.entities.AudioType
import com.twopiradrian.auraluna.domain.repositories.AudiosRepositoryI
import com.twopiradrian.auraluna.infrastructure.datasources.json.JsonAudioDatasource

class AudiosRepository(
    private val audios: JsonAudioDatasource,
    private val mapper: AudioMapper
): AudiosRepositoryI {

    override fun getAll(): List<Audio>? {
        try {
            val models: List<AudioModel> = this.audios.getAll()
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    override fun getById(id: Int): Audio? {
        try {
            val model: AudioModel = this.audios.getById(id)
            return mapper.toDomain(model)
        }
        catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override fun getByCategory(category: AudioCategory): List<Audio>? {
        try {
            val models: List<AudioModel> = this.audios.getByCategory(category.it)
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override fun getByType(type: AudioType): List<Audio>? {
        try {
            val models: List<AudioModel> = this.audios.getByType(type.it)
            return models.map { mapper.toDomain(it) }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}