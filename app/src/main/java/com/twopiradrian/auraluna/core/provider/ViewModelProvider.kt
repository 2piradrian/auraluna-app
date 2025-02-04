package com.twopiradrian.auraluna.core.provider

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.twopiradrian.auraluna.core.container.AppContainer
import com.twopiradrian.auraluna.ui.screens.favorites.viewmodel.FavoritesViewModel
import com.twopiradrian.auraluna.ui.screens.favorites.viewmodel.FavoritesViewModelFactory
import com.twopiradrian.auraluna.ui.screens.home.viewmodel.HomeViewModel
import com.twopiradrian.auraluna.ui.screens.home.viewmodel.HomeViewModelFactory
import com.twopiradrian.auraluna.ui.screens.lineal_player.viewmodel.LinealPlayerViewModel
import com.twopiradrian.auraluna.ui.screens.lineal_player.viewmodel.LinealPlayerViewModelFactory
import com.twopiradrian.auraluna.ui.screens.loop_player.viewmodel.LoopPlayerViewModel
import com.twopiradrian.auraluna.ui.screens.loop_player.viewmodel.LoopPlayerViewModelFactory

class ViewModelProvider(
    private val appContainer: AppContainer
) {

    @Composable
    fun home(navBackStackEntry: NavBackStackEntry): HomeViewModel {
        return viewModel(
            modelClass = HomeViewModel::class.java,
            factory = HomeViewModelFactory(
                appContainer.favoritesRepository,
                appContainer.audiosRepository
            ),
            viewModelStoreOwner = navBackStackEntry
        )
    }

    @Composable
    fun favorites(navBackStackEntry: NavBackStackEntry): FavoritesViewModel {
        return viewModel(
            modelClass = FavoritesViewModel::class.java,
            factory = FavoritesViewModelFactory(
                appContainer.favoritesRepository
            ),
            viewModelStoreOwner = navBackStackEntry
        )
    }

    @Composable
    fun loopPlayer(navBackStackEntry: NavBackStackEntry): LoopPlayerViewModel {
        return viewModel(
            modelClass = LoopPlayerViewModel::class.java,
            factory = LoopPlayerViewModelFactory(
                appContainer.favoritesRepository,
                appContainer.audiosRepository
            ),
            viewModelStoreOwner = navBackStackEntry
        )
    }

    @Composable
    fun linealPlayer(navBackStackEntry: NavBackStackEntry): LinealPlayerViewModel {
        return viewModel(
            modelClass = LinealPlayerViewModel::class.java,
            factory = LinealPlayerViewModelFactory(
                appContainer.favoritesRepository,
                appContainer.audiosRepository
            ),
            viewModelStoreOwner = navBackStackEntry
        )
    }

}