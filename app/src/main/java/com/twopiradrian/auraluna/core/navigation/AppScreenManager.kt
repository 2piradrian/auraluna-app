package com.twopiradrian.auraluna.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.twopiradrian.auraluna.core.container.AppContainer
import com.twopiradrian.auraluna.core.provider.ViewModelProvider
import com.twopiradrian.auraluna.ui.screens.community.CommunityScreen
import com.twopiradrian.auraluna.ui.screens.home.HomeScreen
import com.twopiradrian.auraluna.ui.screens.favorites.FavoritesScreen
import com.twopiradrian.auraluna.ui.screens.lineal_player.LinealPlayerScreen
import com.twopiradrian.auraluna.ui.screens.loop_player.LoopPlayerScreen
import com.twopiradrian.auraluna.ui.utils.ScreenType
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun AppScreenManager(
    appContainer: AppContainer,
    screenType: ScreenType
) {

    val context = LocalContext.current

    val navController: NavHostController = rememberNavController()
    val viewModels = ViewModelProvider(appContainer)
    val screenUtils = ScreenUtils(screenType)

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.route
    ) {
        // Home Screen
        composable(
            route = AppScreens.HomeScreen.route
        ) { navBackStackEntry ->
            val viewModel = viewModels.home(navBackStackEntry)
            HomeScreen(
                navController = navController,
                screenUtils = screenUtils,
                viewModel = viewModel
            )
        }
        // Favorites Screen
        composable(
            route = AppScreens.FavoritesScreen.route
        ) { navBackStackEntry ->
            val viewModel = viewModels.favorites(navBackStackEntry)
            FavoritesScreen(
                navController = navController,
                screenUtils = screenUtils,
                viewModel = viewModel
            )
        }
        // Community Screen
        composable(
            route = AppScreens.CommunityScreen.route
        ) {
            CommunityScreen(
                navController = navController,
                screenUtils = screenUtils
            )
        }
        // Loop Player Screen
        composable(
            route = AppScreens.LoopPlayerScreen.route + "/{audioId}",
            arguments = listOf(
                navArgument("audioId") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val viewModel = viewModels.loopPlayer(navBackStackEntry)
            LoopPlayerScreen(
                context = context,
                screenUtils = screenUtils,
                viewModel = viewModel,
                audioId = navBackStackEntry.arguments?.getInt("audioId") ?: 0
            )
        }
        // Lineal Player Screen
        composable(
            route = AppScreens.LinealPlayerScreen.route + "/{audioId}",
            arguments = listOf(
                navArgument("audioId") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val viewModel = viewModels.linealPlayer(navBackStackEntry)
            LinealPlayerScreen(
                context = context,
                screenUtils = screenUtils,
                viewModel = viewModel,
                audioId = navBackStackEntry.arguments?.getInt("audioId") ?: 0
            )
        }
    }

}