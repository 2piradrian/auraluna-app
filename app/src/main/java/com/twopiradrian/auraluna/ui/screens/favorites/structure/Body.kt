package com.twopiradrian.auraluna.ui.screens.favorites.structure

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.domain.entities.Favorite
import com.twopiradrian.auraluna.ui.components.atoms.TitleLarge
import com.twopiradrian.auraluna.ui.components.molecules.FavoritesList

@Composable
fun FavoritesBody(
    favorites: List<Favorite>,
    toggleFavorite: (Favorite) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        TitleLarge(
            textId = R.string.favorites_title
        )
        FavoritesList(
            favorites = favorites,
            onFavoriteClick = {

            },
            toggleFavorite = {
                toggleFavorite(it)
            }
        )
    }
}