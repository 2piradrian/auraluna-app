package com.twopiradrian.auraluna.ui.screens.community

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.twopiradrian.auraluna.ui.components.organisms.AppNavigationBar
import com.twopiradrian.auraluna.ui.layouts.AppLayout
import com.twopiradrian.auraluna.ui.screens.community.structure.CommunityBody
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun CommunityScreen(
    navController: NavController,
    screenUtils: ScreenUtils
) {
    AppLayout(
        content = {
            CommunityBody()
        },
        bottomBar = {
            AppNavigationBar(
                navController = navController,
            )
        }
    )
}