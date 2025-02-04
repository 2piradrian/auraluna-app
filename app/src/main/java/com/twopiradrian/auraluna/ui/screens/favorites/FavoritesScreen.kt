package com.twopiradrian.auraluna.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.twopiradrian.auraluna.ui.components.organisms.AppNavigationBar
import com.twopiradrian.auraluna.ui.layouts.AppLayout
import com.twopiradrian.auraluna.ui.screens.favorites.structure.FavoritesBody
import com.twopiradrian.auraluna.ui.screens.favorites.viewmodel.FavoritesViewModel
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun FavoritesScreen(
    navController: NavController,
    screenUtils: ScreenUtils,
    viewModel: FavoritesViewModel
) {

    val favorites by viewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAudios()
    }

    AppLayout(
        content = {
            FavoritesBody(
                favorites = favorites,
                toggleFavorite = {
                    viewModel.toggleFavorite(it)
                },
                modifier = Modifier.fillMaxSize()
            )
        },
        bottomBar = {
            AppNavigationBar(
                navController = navController,
            )
        }
    )
}