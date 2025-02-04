package com.twopiradrian.auraluna.ui.screens.lineal_player

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.twopiradrian.auraluna.ui.layouts.AppLayout
import com.twopiradrian.auraluna.ui.screens.lineal_player.structure.LinealPlayerBody
import com.twopiradrian.auraluna.ui.screens.lineal_player.viewmodel.LinealPlayerViewModel
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun LinealPlayerScreen(
    context: Context,
    screenUtils: ScreenUtils,
    viewModel: LinealPlayerViewModel,
    audioId: Int
) {

    val audio by viewModel.audio.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val isReady by viewModel.isReady.collectAsState()

    val error by viewModel.error.collectAsState()

    val position by viewModel.position.collectAsState()
    val duration by viewModel.duration.collectAsState()
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
                LinealPlayerBody(
                    audio = it,
                    screenUtils = screenUtils,
                    context = context,
                    isFavorite = isFavorite,
                    position = position,
                    duration = duration,
                    isPlaying = isPlaying,
                    isReady = isReady,
                    toggleFavorite = {
                        viewModel.toggleFavorite()
                    },
                    setReady = {
                        viewModel.setReady()
                    },
                    updatePlayerState = {
                        exo -> viewModel.updatePlayerState(exo)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    )

}