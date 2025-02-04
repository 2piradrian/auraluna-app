package com.twopiradrian.auraluna.ui.components.organisms

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.twopiradrian.auraluna.domain.entities.Audio
import com.twopiradrian.auraluna.ui.components.molecules.AudioCover

@Composable
fun AudioCoverList(
    titleId: Int,
    list: List<Audio>,
    onClick: (Audio) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ){
            list.forEach { audio ->
                AudioCover(
                    audio = audio,
                    onClick = {
                        onClick(audio)
                    }
                )
            }
        }
    }
}