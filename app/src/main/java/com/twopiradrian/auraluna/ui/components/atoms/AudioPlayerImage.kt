package com.twopiradrian.auraluna.ui.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.twopiradrian.auraluna.ui.utils.ScreenUtils

@Composable
fun AudioPlayerImage(
    coverId: Int,
    screenUtils: ScreenUtils
) {
    Box(
        modifier = Modifier
            //.size(screenUtils.resolveSize(360.dp, 380.dp, 400.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .aspectRatio(ratio = 1f)
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = coverId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )
    }
}