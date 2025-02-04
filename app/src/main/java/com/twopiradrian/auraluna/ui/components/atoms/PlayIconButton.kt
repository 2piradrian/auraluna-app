package com.twopiradrian.auraluna.ui.components.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.twopiradrian.auraluna.R

@Composable
fun PlayIconButton(
    onClick: () -> Unit,
    isPlaying: Boolean,
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = onClick,
        content = {
            if(isPlaying){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_pause),
                    contentDescription = stringResource(R.string.player_pause)
                )
            }
            else{
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_play),
                    contentDescription = stringResource(R.string.player_play)
                )
            }
        }
    )
}