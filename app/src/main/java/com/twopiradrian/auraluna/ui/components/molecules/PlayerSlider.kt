package com.twopiradrian.auraluna.ui.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import com.twopiradrian.auraluna.core.utils.TemplateUtils

// This component must have the same height as DurationList
@Composable
fun PlayerSlider(
    position: Long,
    duration: Long,
    exoPlayer: ExoPlayer
) {
    Column(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = TemplateUtils.convertTime(position),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = TemplateUtils.convertTime(duration),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Slider(
            value = (position / 1000).toFloat(),
            valueRange = 0f..(duration / 1000).toFloat(),
            onValueChange = {
                exoPlayer.seekTo((it * 1000).toLong())
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}