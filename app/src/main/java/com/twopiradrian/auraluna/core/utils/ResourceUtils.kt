package com.twopiradrian.auraluna.core.utils

import android.content.Context


class ResourceUtils(
    private val context: Context
) {

    fun getDrawableResourceId(resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }

    fun getRawResourceId(resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "raw", context.packageName)
    }

    fun getResourceName(resourceId: Int, type: String): String {
        return when (type) {
            "drawable" -> context.resources.getResourceEntryName(resourceId)
            "raw" -> context.resources.getResourceEntryName(resourceId)
            else -> throw IllegalArgumentException("Unknown resource type: $type")
        }
    }

}