package com.application.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.application.pokedex.models.Result
import com.application.pokedex.screens.pokemons_list_screen.PokemonsListViewModel
import com.application.pokedex.util.getDominantColor
import com.application.pokedex.util.getImageUrl

@Composable
fun PokemonCard(
    index: Int,
    onPokemonClick: (Result, Int) -> Unit,
    pokemonResult: Result,
    viewModel: PokemonsListViewModel,
) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .height(240.dp)
            .width(85.dp)
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.DarkGray)
            .border(
                width = Dp.Hairline,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        if (index == screenState.items.size && !screenState.endReached && !screenState.isLoading) {
            viewModel.loadNextPokemons()
        }

        var dominantColor by remember {
            mutableStateOf(Color.DarkGray)
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = dominantColor,
                    shape = RoundedCornerShape(10.dp)
                )
        )


        AsyncImage(
            modifier = Modifier
                .clickable { onPokemonClick(pokemonResult, pokemonResult.itemNumber) },
            model = getImageUrl(pokemonResult.itemNumber),
            contentDescription = null,
            onSuccess = { imagePainterState ->
                val colorCode = getDominantColor(imagePainterState.result.drawable)
                dominantColor = Color(colorCode)
            }
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
                .background(color = MaterialTheme.colorScheme.background),
            text = pokemonResult.name.capitalize(Locale.current),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
        )
    }
}