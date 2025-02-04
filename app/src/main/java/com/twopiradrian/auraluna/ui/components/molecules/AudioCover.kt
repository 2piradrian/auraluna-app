package com.twopiradrian.auraluna.ui.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.twopiradrian.auraluna.domain.entities.Audio

@Composable
fun AudioCover(
    audio: Audio,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(MaterialTheme.colorScheme.surface)
                .aspectRatio(ratio = 1f)
                .padding(vertical = 2.dp)
                .clickable {
                    onClick()
                }
        ) {
            Image(
                painter = painterResource(id = audio.coverResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Text(
            text = audio.name,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = audio.author,
            style = MaterialTheme.typography.bodySmall
        )
    }
}