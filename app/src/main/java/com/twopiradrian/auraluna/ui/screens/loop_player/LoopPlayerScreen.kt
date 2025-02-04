package com.twopiradrian.auraluna.ui.screens.loop_player

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.media3.exoplayer.ExoPlayer
import com.twopiradrian.auraluna.ui.layouts.AppLayout
import com.twopiradrian.auraluna.ui.screens.loop_player.structure.LoopPlayerBody
import com.twopiradrian.auraluna.ui.screens.loop_player.viewmodel.LoopPlayerViewModel
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun LoopPlayerScreen(
    context: Context,
    screenUtils: ScreenUtils,
    viewModel: LoopPlayerViewModel,
    audioId: Int
) {

    val audio by viewModel.audio.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val isReady by viewModel.isReady.collectAsState()

    val error by viewModel.error.collectAsState()

    val durations by viewModel.durations.collectAsState()
    val selectedTimes by viewModel.selectedTimes.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAudio(audioId)
    }

    LaunchedEffect(error) {
        if(error != null){
            Toast.makeText(context, context.getString(error!!), Toast.LENGTH_SHORT).show()
            viewModel.setErrorToNull()
        }
    }

    AppLayout(
        content = {
            audio?.let {
                LoopPlayerBody(
                    audio = it,
                    screenUtils = screenUtils,
                    context = context,
                    isFavorite = isFavorite,
                    durations = durations,
                    selectedTimes = selectedTimes,
                    isReady = isReady,
                    isPlaying = isPlaying,
                    toggleFavorite = {
                        viewModel.toggleFavorite()
                    },
                    setReady = {
                        viewModel.setReady()
                    },
                    setDurations = {
                        duration: Int, times: List<Int> -> viewModel.setDurations(duration, times)
                    },
                    setSelectedDuration = {
                        duration: Int -> viewModel.setSelectedDuration(duration)
                    },
                    updatePlayerState = {
                        exo: ExoPlayer -> viewModel.updatePlayerState(exo)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    )

}