package com.twopiradrian.auraluna.ui.components.molecules

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.twopiradrian.auraluna.core.utils.TemplateUtils
import com.twopiradrian.auraluna.ui.components.atoms.Chip

// This component must have the same height as PlayerSlider
@Composable
fun DurationList(
    selected: Int,
    durations: List<Int>,
    times: List<Int>,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        durations.forEach { duration ->
            Chip(
                onClick = {
                    onClick(times[durations.indexOf(duration)])
                },
                text = ("${TemplateUtils.convertTime(duration)} min"),
                selected = selected == times[durations.indexOf(duration)]
            )
        }
    }
}