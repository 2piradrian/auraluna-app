package com.twopiradrian.auraluna.core.utils

class TemplateUtils {
    companion object {

        fun convertTime(value: Long): String {
            val minutes: Long = (value / 1000) / 60
            val seconds: Long = (value / 1000) % 60

            val formattedSeconds = if (seconds < 10) "0$seconds" else "$seconds"

            return "${minutes}:${formattedSeconds}"
        }

        fun convertTime(value: Int): String {
            val minutes: Int = (value) / 60
            val seconds: Int = (value) % 60

            val formattedSeconds = if (seconds < 10) "0$seconds" else "$seconds"

            return "${minutes}:${formattedSeconds}"
        }

        fun getDuration(value: String): Int {
            val time = value.split(":")

            val minutes = time[0].toInt()
            val seconds = if (time.size > 1) time[1].toInt() else 0

            return minutes * 60 + seconds
        }

    }
}