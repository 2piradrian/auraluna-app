package com.twopiradrian.auraluna.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.twopiradrian.auraluna.infrastructure.repositories.AudiosRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoritesRepository

class HomeViewModelFactory(
    private val favoritesRepository: FavoritesRepository,
    private val audiosRepository: AudiosRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                favoritesRepository = favoritesRepository,
                audiosRepository = audiosRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}