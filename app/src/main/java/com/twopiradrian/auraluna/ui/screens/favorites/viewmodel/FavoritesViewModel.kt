package com.twopiradrian.auraluna.ui.screens.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.Favorite
import com.twopiradrian.auraluna.infrastructure.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites

    fun getAudios() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favoritesRes = favoritesRepository.getAll()
                if (favoritesRes.isEmpty()) {
                    _favorites.value = emptyList()
                    return@withContext
                }

                _favorites.value = favoritesRes
            }
        }
    }

    fun toggleFavorite(favorite: Favorite) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fav: Favorite? = favoritesRepository.getById(favorite.audioId)
                if (fav != null) {
                    favoritesRepository.delete(fav)
                    getAudios()
                    return@withContext
                }
            }
        }
    }

}