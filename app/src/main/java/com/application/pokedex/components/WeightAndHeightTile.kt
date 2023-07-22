package com.application.pokedex.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WeightAndHeightTile(
    painter: Painter,
    category: String,
    value: String
) {
    Row {
        Icon(painter = painter, contentDescription = null)

        Column {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = category,
                style = MaterialTheme.typography.titleSmall,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
            )
        }
    }
}