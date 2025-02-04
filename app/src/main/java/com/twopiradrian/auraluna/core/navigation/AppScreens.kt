package com.twopiradrian.auraluna.core.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen : AppScreens("home_screen")
    data object FavoritesScreen : AppScreens("favorites_screen")
    data object CommunityScreen : AppScreens("community_screen")
    data object LoopPlayerScreen : AppScreens("loop_player_screen")
    data object LinealPlayerScreen : AppScreens("lineal_player_screen")
}