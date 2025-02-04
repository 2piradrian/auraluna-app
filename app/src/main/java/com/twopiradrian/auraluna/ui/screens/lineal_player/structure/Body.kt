package com.twopiradrian.auraluna.ui.screens.lineal_player.structure

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.ui.components.atoms.AudioPlayerImage
import com.twopiradrian.auraluna.ui.components.atoms.PlayIconButton
import com.twopiradrian.auraluna.ui.components.molecules.AudioPlayerDescription
import com.twopiradrian.auraluna.ui.components.molecules.PlayerSlider
import com.twopiradrian.auraluna.ui.utils.ScreenUtils
import kotlinx.coroutines.delay

@Composable
fun LinealPlayerBody(
    audio: Audio,
    screenUtils: ScreenUtils,
    context: Context,
    isFavorite: Boolean,
    position: Long,
    duration: Long,
    isPlaying: Boolean,
    isReady: Boolean,
    toggleFavorite: () -> Unit,
    setReady: () -> Unit,
    updatePlayerState: (ExoPlayer) -> Unit,
    modifier: Modifier
) {

    val exoPlayer = remember(audio) {
        ExoPlayer.Builder(context).build().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .build(),
                true
            )
            val rawUri = Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(context.packageName)
                .appendPath(audio.audioResource.toString())
                .build()
            setMediaItem(MediaItem.fromUri(rawUri))
            prepare()

            var isReadyCalled = false
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY && !isReadyCalled) {
                        isReadyCalled = true
                        setReady()
                    }
                }
            })
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.stop()
            exoPlayer.release()
        }
    }

    LaunchedEffect(exoPlayer) {
        while (true) {
            updatePlayerState(exoPlayer)
            delay(500L)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = screenUtils.resolveSize(380.dp, 380.dp, 400.dp))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AudioPlayerImage(
                coverId = audio.coverResource,
                screenUtils = screenUtils
            )
            AudioPlayerDescription(
                name = audio.name,
                author = audio.author,
                liked = isFavorite,
                toggleFavorite = toggleFavorite
            )
            PlayerSlider(
                position = position,
                duration = duration,
                exoPlayer = exoPlayer
            )
            PlayIconButton(
                onClick = { if (isReady) { if (isPlaying) exoPlayer.pause() else exoPlayer.play() } },
                isPlaying = isPlaying
            )
        }
    }
}