package com.twopiradrian.auraluna.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.twopiradrian.auraluna.ui.components.organisms.AppNavigationBar
import com.twopiradrian.auraluna.ui.layouts.AppLayout
import com.twopiradrian.auraluna.ui.screens.home.structure.HomeBody
import com.twopiradrian.auraluna.ui.screens.home.viewmodel.HomeViewModel
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun HomeScreen(
    navController: NavController,
    screenUtils: ScreenUtils,
    viewModel: HomeViewModel
) {

    val audios by viewModel.audios.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAudios()
    }

    AppLayout(
        content = {
            HomeBody(
                audios = audios,
                navController = navController,
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