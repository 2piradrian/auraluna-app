package com.twopiradrian.auraluna.ui.utils

import com.twopiradrian.auraluna.core.navigation.AppScreens
import com.twopiradrian.auraluna.domain.entities.AudioType

class RouteUtils {

    companion object {
        val audioRoutes = mapOf(
            AudioType.LOOP to AppScreens.LoopPlayerScreen.route,
            AudioType.LINEAL to AppScreens.LinealPlayerScreen.route
        )
    }

}