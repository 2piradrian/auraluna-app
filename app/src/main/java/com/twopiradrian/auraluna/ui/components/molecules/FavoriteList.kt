package com.twopiradrian.auraluna.ui.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.twopiradrian.auraluna.domain.entities.Favorite
import com.twopiradrian.auraluna.ui.components.atoms.FavoriteItem

@Composable
fun FavoritesList(
    favorites: List<Favorite>,
    onFavoriteClick: (Favorite) -> Unit,
    toggleFavorite: (Favorite) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        favorites.forEach { audio ->
            FavoriteItem(
                name = audio.name,
                author = audio.author,
                onClick = {
                    onFavoriteClick(audio)
                },
                toggleFavorite = {
                    toggleFavorite(audio)
                }
            )
        }
    }
}