package com.twopiradrian.auraluna.ui.components.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    onClick: () -> Unit,
    textId: Int? = null,
    text: String? = null,
    selected: Boolean = false
) {
    FilterChip(
        onClick = onClick,
        label = {
            textId?.let {
                Text(stringResource(id = textId))
            }
            text?.let {
                Text(text)
            }
        },
        selected = selected,
        modifier = Modifier.padding(horizontal = 2.dp)
    )
}