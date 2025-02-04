package com.twopiradrian.auraluna.domain.entities

import com.twopiradrian.auraluna.R

enum class AudioCategory(
    val it: String,
    val id: Int
) {
    DISTRESS("DISTRESS", R.string.distress),    // Angustia
    ANXIETY("ANXIETY", R.string.anxiety),       // Ansiedad
    STRESS("STRESS", R.string.stress),          // Estres
    HAPPINESS("HAPPINESS", R.string.happiness), // Alegría
    ANGER("ANGER", R.string.anger);             // Enojo

    companion object {
        fun toList(): List<AudioCategory> {
            return listOf(DISTRESS, ANXIETY, STRESS, HAPPINESS, ANGER)
        }
    }

}