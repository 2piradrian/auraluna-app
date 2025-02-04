package com.twopiradrian.auraluna.ui.screens.loop_player.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.twopiradrian.auraluna.R
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.domain.entities.Favorite
import com.twopiradrian.auraluna.infrastructure.repositories.AudiosRepository
import com.twopiradrian.auraluna.infrastructure.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoopPlayerViewModel(
    private val favoritesRepository: FavoritesRepository,
    private val audiosRepository: AudiosRepository
) : ViewModel() {

    private val _audio = MutableStateFlow<Audio?>(null)
    val audio: StateFlow<Audio?> = _audio

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val _durations = MutableStateFlow<List<Int>>(emptyList())
    val durations: StateFlow<List<Int>> = _durations

    private val _selectedTimes = MutableStateFlow(0)
    val selectedTimes: StateFlow<Int> = _selectedTimes

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _error = MutableStateFlow<Int?>(null)
    val error: StateFlow<Int?> = _error

    fun getAudio(audioId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val audioResult: Audio? = audiosRepository.getById(audioId)
                if (audioResult == null) {
                    _error.value = R.string.error_audio_not_found
                    return@withContext
                }

                val favResult: Favorite? = favoritesRepository.getById(audioResult.id)
                if (favResult != null) {
                    _isFavorite.value = true
                }

                _audio.value = audioResult
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val audio = _audio.value ?: return@withContext
                val fav: Favorite? = favoritesRepository.getById(audio.id)

                if (fav != null) {
                    favoritesRepository.delete(fav)
                    _isFavorite.value = false
                }
                else {
                    favoritesRepository.insert(Favorite(audioId = audio.id, name = audio.name, author = audio.author))
                    _isFavorite.value = true
                }
            }
        }
    }

    fun setErrorToNull() {
        _error.value = null
    }

    fun setReady(){
        _isReady.value = true
    }

    fun updatePlayerState(exoPlayer: ExoPlayer){
        _isPlaying.value = exoPlayer.isPlaying
    }

    fun setDurations(duration: Int, times: List<Int>) {
        _durations.value = times.map { it * duration }
        _selectedTimes.value = times.first()
    }

    fun setSelectedDuration(duration: Int) {
        _selectedTimes.value = duration
    }

}