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
import com.twopiradrian.auraluna.domain.entities.AudioCategory
import com.twopiradrian.auraluna.ui.components.atoms.Chip

@Composable
fun CategoriesList(
    selected: List<AudioCategory>,
    categories: List<AudioCategory>,
    onClick: (AudioCategory) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        categories.forEach { category ->
            Chip(
                onClick = {
                    onClick(category)
                },
                textId = category.id,
                selected = selected.contains(category)
            )
        }
    }
}