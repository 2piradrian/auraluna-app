package com.twopiradrian.auraluna.ui.components.organisms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.twopiradrian.auraluna.core.navigation.AppScreens
import com.twopiradrian.auraluna.R

sealed class MenuItem(
    val route: String,
    val icon: Int,
    val label: Int
) {
    companion object {
        val items = listOf(Home, Favorites, Community)
    }

    data object Home: MenuItem(
        AppScreens.HomeScreen.route,
        R.drawable.ic_home,
        R.string.nav_home
    )

    data object Favorites: MenuItem(
        AppScreens.FavoritesScreen.route,
        R.drawable.ic_favorite,
        R.string.nav_favorites
    )

    data object Community: MenuItem(
        AppScreens.CommunityScreen.route,
        R.drawable.ic_community,
        R.string.nav_community
    )

}

@Composable
fun AppNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = remember { MenuItem.items }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = null,
                        tint = if (currentRoute == item.route)
                            MaterialTheme.colorScheme.onBackground
                        else
                            MaterialTheme.colorScheme.onPrimary
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.label),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    }
}

