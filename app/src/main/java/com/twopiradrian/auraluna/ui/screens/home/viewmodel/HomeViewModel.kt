package com.twopiradrian.auraluna.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.infrastructure.repositories.AudiosRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val favoritesRepository: FavoritesRepository,
    private val audiosRepository: AudiosRepository
) : ViewModel() {

    private val _audios = MutableStateFlow(emptyList<Audio>())
    val audios: StateFlow<List<Audio>> = _audios

    private val _error = MutableStateFlow<Int?>(null)
    val error: StateFlow<Int?> = _error

    fun getAudios() {
        viewModelScope.launch {
            val audiosResult: List<Audio>? = audiosRepository.getAll()
            if (audiosResult == null) {
                _error.value = R.string.error_loading_audios
                return@launch
            }

            _audios.value = audiosResult
        }
    }

}