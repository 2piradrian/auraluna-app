package com.twopiradrian.auraluna.data.json.mapper

import com.twopiradrian.auraluna.core.utils.ResourceUtils
import com.twopiradrian.auraluna.data.json.model.AudioModel
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.domain.entities.AudioFormat
import com.twopiradrian.auraluna.domain.entities.AudioType

class AudioMapper(
    private val resourceUtils: ResourceUtils
) {

    fun toDomain(model: AudioModel): Audio {
        return Audio(
            id = model.id,
            name = model.name,
            author = model.author,
            coverResource = resourceUtils.getDrawableResourceId(model.coverResource),
            audioResource = resourceUtils.getRawResourceId(model.audioResource),
            type = AudioType.valueOf(model.type),
            format = AudioFormat.valueOf(model.format),
            categories = model.categories.map { AudioCategory.valueOf(it) },
            times = model.times,
            duration = model.duration
        )
    }

    fun toModel(domain: Audio): AudioModel {
        return AudioModel(
            id = domain.id,
            name = domain.name,
            author = domain.author,
            coverResource = resourceUtils.getResourceName(domain.coverResource, "drawable"),
            audioResource = resourceUtils.getResourceName(domain.audioResource, "raw"),
            type = domain.type.name,
            format = domain.format.name,
            categories = domain.categories.map { it.name },
            times = domain.times,
            duration = domain.duration
        )
    }

}