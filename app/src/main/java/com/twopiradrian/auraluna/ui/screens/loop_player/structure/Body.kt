package com.twopiradrian.auraluna.ui.screens.loop_player.structure

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.twopiradrian.auraluna.core.utils.TemplateUtils
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.ui.components.atoms.AudioPlayerImage
import com.twopiradrian.auraluna.ui.components.atoms.PlayIconButton
import com.twopiradrian.auraluna.ui.components.molecules.AudioPlayerDescription
import com.twopiradrian.auraluna.ui.components.molecules.DurationList
import com.twopiradrian.auraluna.ui.utils.ScreenUtils
import kotlinx.coroutines.delay

@Composable
fun LoopPlayerBody(
    audio: Audio,
    screenUtils: ScreenUtils,
    context: Context,
    isFavorite: Boolean,
    durations: List<Int>,
    selectedTimes: Int,
    isReady: Boolean,
    isPlaying: Boolean,
    toggleFavorite: () -> Unit,
    setReady: () -> Unit,
    setDurations: (Int, List<Int>) -> Unit,
    setSelectedDuration: (Int) -> Unit,
    updatePlayerState: (ExoPlayer) -> Unit,
    modifier: Modifier,
) {

    val exoPlayer = remember(audio, selectedTimes) {
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

            val mediaItems = List(selectedTimes) {
                MediaItem.fromUri(rawUri)
            }

            setMediaItems(mediaItems)
            prepare()

            var isReadyCalled = false

            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY && !isReadyCalled) {
                        isReadyCalled = true
                        setReady()
                    }
                    if (playbackState == Player.STATE_ENDED) {
                        seekToDefaultPosition()
                        setMediaItems(mediaItems)
                        prepare()
                        pause()
                    }
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    updatePlayerState(this@apply)
                }
            })
        }
    }

    LaunchedEffect(audio) {
        setDurations(TemplateUtils.getDuration(audio.duration), audio.times)
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.stop()
            exoPlayer.release()
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = screenUtils.resolveSize(380.dp, 390.dp, 400.dp))
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
            DurationList(
                onClick = {
                    exoPlayer.pause()
                    exoPlayer.seekTo(0L)
                    setSelectedDuration(it)
                },
                selected = selectedTimes,
                durations = durations,
                times = audio.times
            )
            PlayIconButton(
                onClick = { if (isReady) { if (isPlaying) exoPlayer.pause() else exoPlayer.play() } },
                isPlaying = isPlaying
            )
        }
    }
}

