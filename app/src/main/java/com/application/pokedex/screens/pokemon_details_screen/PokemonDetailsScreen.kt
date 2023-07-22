package com.application.pokedex.screens.pokemon_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.application.pokedex.R
import com.application.pokedex.components.AnimatedImage
import com.application.pokedex.components.BaseStatBar
import com.application.pokedex.components.TopHeader
import com.application.pokedex.components.WeightAndHeightTile
import com.application.pokedex.models.Pokemon
import com.application.pokedex.models.Result
import com.application.pokedex.screens.ErrorScreen
import com.application.pokedex.util.Response
import com.application.pokedex.util.darkenColor
import com.application.pokedex.util.getDominantColor
import com.application.pokedex.util.getImageUrl
import com.application.pokedex.util.getPokemonTypeColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

// TODO Try Scaffold.....

@Destination
@Composable
fun PokemonsDetailsScreen(
    navigator: DestinationsNavigator,
    pokemonResult: Result
) {
    val viewModel = hiltViewModel<PokemonDetailsViewModel>()
    LaunchedEffect(key1 = null) {
        viewModel.getPokemonDetails(pokemonResult)
    }

    val pokemonDetailsResponse
            by viewModel.pokemonDetailsState.collectAsState()

    when (pokemonDetailsResponse) {
        is Response.Loading -> {
            Column(modifier = Modifier.fillMaxSize()) {
                TopHeader(modifier = Modifier.align(Alignment.Start)) {
                    navigator.popBackStack()
                }

                val screenHeight = LocalConfiguration.current.screenHeightDp
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(y = (screenHeight * 0.4).dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        is Response.Success -> ShowPokemonDetails(
            navigator = navigator,
            pokemonResult = pokemonResult,
            pokemon = pokemonDetailsResponse.data ?: Pokemon()
        )

        is Response.Error -> {
            ErrorScreen(detailedError = pokemonDetailsResponse.error ?: "error")
        }
    }
}


@Composable
fun ShowPokemonDetails(navigator: DestinationsNavigator, pokemonResult: Result, pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        ImageAndNameSection(navigator, pokemonResult, pokemon)

        Spacer(modifier = Modifier.height(30.dp))

        PokemonDetailsSection(pokemonResult = pokemonResult, pokemon = pokemon)
    }
}

@Composable
fun ImageAndNameSection(navigator: DestinationsNavigator, pokemonResult: Result, pokemon: Pokemon) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val initialBackground = MaterialTheme.colorScheme.background
    var dominantColor by remember {
        mutableStateOf(initialBackground)
    }

    val pokemonName = pokemon.name.capitalize(Locale.current)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(modifier = Modifier.align(Alignment.Start), headerText = pokemonName) {
            navigator.popBackStack()
        }


        AnimatedImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            darkenColor(dominantColor, 0.7f),
                            MaterialTheme.colorScheme.background,
                        )
                    )
                )
                .padding((screenHeight * 0.075).dp),
            model = getImageUrl(pokemonResult.itemNumber)
        ) {
            val drawable = it.result.drawable
            val colorCode = getDominantColor(drawable)

            dominantColor = Color(colorCode)
        }


        Text(
            text = pokemonName,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = when (pokemon.id) {
                in 0..9 -> "#00${pokemon.id}"
                in 10..99 -> "#0${pokemon.id}"
                else -> "#${pokemon.id}"
            },
            style = MaterialTheme.typography.titleSmall,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            pokemon.types.forEach { pokemonType ->
                Box(
                    modifier = Modifier
                        .background(
                            color = getPokemonTypeColor(pokemonType),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .align(Alignment.Center),
                        text = pokemonType.type.name.capitalize(Locale.current),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}


@Composable
fun PokemonDetailsSection(
//    navigator: DestinationsNavigator,
    pokemonResult: Result,
    pokemon: Pokemon
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Height and weight
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.8f)
                .background(
                    color = if (isSystemInDarkTheme()) Color(0xFF343A2E) else Color(0xFF92BB7F),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(vertical = 20.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            WeightAndHeightTile(
                painter = painterResource(id = R.drawable.weight_icon),
                category = "Weight",
                value = "${pokemon.weight / 10f} kg"
            )

            Box(
                modifier = Modifier
                    .width(52.dp)
                    .height(30.dp)
                    .padding(horizontal = 25.dp)
                    .background(color = Color.White)
            )

            WeightAndHeightTile(
                painter = painterResource(id = R.drawable.height_icon),
                category = "Height",
                value = "${pokemon.height / 10f} m"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Base stats
        Column(
            modifier = Modifier
                .fillMaxWidth(0.93f)
                .background(
                    color = if (isSystemInDarkTheme()) Color(0xB7343632) else Color(0x809AC785),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(top = 20.dp, start = 18.dp, end = 5.dp, bottom = 8.dp)
        ) {
            val statsMaxValue = pokemon.stats.maxOf { it.base_stat }
            pokemon.stats.forEach { stat ->
                BaseStatBar(stat = stat, statsMaxValue = statsMaxValue)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}