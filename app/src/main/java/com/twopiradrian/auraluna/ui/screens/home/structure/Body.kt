package com.twopiradrian.auraluna.ui.screens.home.structure

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.core.navigation.AppScreens
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.domain.entities.AudioType
import com.twopiradrian.auraluna.ui.components.atoms.TitleLarge
import com.twopiradrian.auraluna.ui.components.molecules.CategoriesList
import com.twopiradrian.auraluna.ui.components.organisms.AudioCoverList
import com.twopiradrian.auraluna.ui.utils.RouteUtils

@Composable
fun HomeBody(
    audios: List<Audio>,
    navController: NavController,
    modifier: Modifier
) {
    Column (
        modifier = modifier
    ){
        TitleLarge(
            textId = R.string.home_title
        )
        CategoriesList(
            selected = emptyList(),
            categories = AudioCategory.toList(),
            onClick = { }
        )
        AudioCoverList(
            list = audios,
            titleId = R.string.home_takeyourtime,
            onClick = {
                RouteUtils.audioRoutes[it.type]?.let { route ->
                    navController.navigate("$route/${it.id}") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}