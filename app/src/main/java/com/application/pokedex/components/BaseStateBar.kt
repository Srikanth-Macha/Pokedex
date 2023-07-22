package com.application.pokedex.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.application.pokedex.models.Pokemon
import com.application.pokedex.util.darkenColor
import com.application.pokedex.util.getPokemonStatColor
import com.application.pokedex.util.getStatAbbreviation

@Composable
fun BaseStatBar(
    modifier: Modifier = Modifier,
    stat: Pokemon.Stat,
    statsMaxValue: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var imageAnimated by remember {
            mutableStateOf(false)
        }

        var textAnimated by remember {
            mutableStateOf(false)
        }

        val durationMillis = 1200
        val delayMillis = 500

        val currentStatValue = animateIntAsState(
            targetValue = if (textAnimated) stat.base_stat else 0,
            animationSpec = tween(durationMillis = durationMillis, delayMillis = delayMillis)
        )

        val currentPercentage = animateFloatAsState(
            targetValue = if (imageAnimated) stat.base_stat / statsMaxValue.toFloat() else 0f,
            animationSpec = tween(durationMillis = durationMillis, delayMillis = delayMillis)
        )

        Text(
            modifier = Modifier.width(50.dp),
            text = getStatAbbreviation(stat.stat.name),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier
                .height(9.dp)
                .fillMaxWidth(0.8f)
                .background(
                    color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                    shape = CircleShape
                )
        ) {
            LaunchedEffect(key1 = null) {
                imageAnimated = true
                textAnimated = true
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(currentPercentage.value)
                    .background(
                        color = if (isSystemInDarkTheme())
                            getPokemonStatColor(stat)
                        else darkenColor(getPokemonStatColor(stat), 0.9f),
                        shape = CircleShape
                    )
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            modifier = Modifier
                .width(35.dp)
                .padding(end = 5.dp),
            text = currentStatValue.value.toString(),
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Visible
        )
    }
}