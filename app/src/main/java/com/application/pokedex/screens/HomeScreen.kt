package com.application.pokedex.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.pokedex.R
import com.application.pokedex.components.SearchTextField
import com.application.pokedex.screens.destinations.EvolutionsScreensDestination
import com.application.pokedex.screens.destinations.PokemonsListScreenDestination
import com.application.pokedex.ui.theme.OpenSansFontFamily
import com.application.pokedex.ui.theme.OptionsBlue
import com.application.pokedex.ui.theme.OptionsGreen
import com.application.pokedex.ui.theme.OptionsRed
import com.application.pokedex.ui.theme.OptionsYellow
import com.application.pokedex.util.darkenColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val isScreenPortrait =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isScreenPortrait) {
        VerticalHomeScreen(navigator)
    } else {
        HorizontalHomeScreen(navigator)
    }
}

@Composable
fun VerticalHomeScreen(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeAndSearchSection(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            navigator = navigator
        )

        Spacer(modifier = Modifier.height(25.dp))

        OptionsMenu(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pokedexOnClick = {
                navigator.navigate(PokemonsListScreenDestination())
            },
            itemsOnClick = {
                navigator.navigate(EvolutionsScreensDestination)
            },
            evolutionsOnClick = {
                navigator.navigate(EvolutionsScreensDestination)
            },
            movesOnClick = {
                navigator.navigate(EvolutionsScreensDestination)
            },
        )
    }
}

@Composable
fun HorizontalHomeScreen(navigator: DestinationsNavigator) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WelcomeAndSearchSection(
            modifier = Modifier.align(Alignment.CenterVertically),
            navigator = navigator
        )

        OptionsMenu(
            modifier = Modifier.align(Alignment.CenterVertically),
            pokedexOnClick = {
                navigator.navigate(PokemonsListScreenDestination())
            },
            itemsOnClick = {
                navigator.navigate(EvolutionsScreensDestination)
            },
            evolutionsOnClick = {
                navigator.navigate(EvolutionsScreensDestination)
            },
            movesOnClick = {
                navigator.navigate(EvolutionsScreensDestination)
            },
        )
    }
}

@Composable
fun WelcomeAndSearchSection(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val isScreenLandscape =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    var searchText by remember {
        mutableStateOf("")
    }

    val searchModifier = if (isScreenLandscape) {
        modifier
            .fillMaxHeight()
            .fillMaxWidth(0.45f)
            .offset(y = (screenHeight * 0.1).dp)
    } else {
        modifier.fillMaxHeight(0.4f)
    }

    Column(
        modifier = searchModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height((screenHeight * 0.1).dp))

        Text(
            text = "Welcome to the Pokemon World!",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 38.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontFamily = OpenSansFontFamily,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height((screenHeight * 0.06).dp))

        SearchTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            value = searchText,
            onValueChange = { searchText = it },
            label = "Search"
        ) {
            if (searchText.isEmpty()) {
                Toast.makeText(context, "Please enter a pokemon name", Toast.LENGTH_SHORT).show()
            } else {
                navigator.navigate(PokemonsListScreenDestination(searchText))
            }
        }
    }
}

@Composable
fun OptionsMenu(
    modifier: Modifier = Modifier,
    pokedexOnClick: () -> Unit,
    itemsOnClick: () -> Unit,
    evolutionsOnClick: () -> Unit,
    movesOnClick: () -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isScreenLandscape =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val cornerRadius = 12.dp

    val boxElements = listOf(
        BoxElement(
            name = "Pokemons",
            color = OptionsRed,
            alignment = Alignment.TopStart,
            iconId = R.drawable.pokeball,
            onClick = pokedexOnClick
        ),
        BoxElement(
            name = "Items",
            color = OptionsYellow,
            alignment = Alignment.TopEnd,
            iconId = R.drawable.electric,
            onClick = itemsOnClick
        ),
        BoxElement(
            name = "Evolutions",
            color = OptionsGreen,
            alignment = Alignment.BottomStart,
            iconId = R.drawable.dna,
            onClick = evolutionsOnClick
        ),
        BoxElement(
            name = "Locations",
            color = OptionsBlue,
            alignment = Alignment.BottomEnd,
            iconId = R.drawable.location,
            onClick = movesOnClick
        )
    )

    val boxContainerModifier = if (isScreenLandscape) {
        modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(0.83f)
    } else {
        modifier
            .fillMaxHeight(0.31f)
            .fillMaxWidth(0.98f)
    }


    Box(
        modifier = boxContainerModifier
    ) {
        val boxModifier = if (isScreenLandscape) {
            Modifier
                .height((screenHeight * 0.18).dp)
                .width((screenWidth * 0.225).dp)
                .padding(horizontal = 5.dp)
        } else {
            Modifier
                .height((screenHeight * 0.074).dp)
                .width((screenWidth * 0.5).dp)
                .padding(horizontal = 15.dp)
        }

        for (boxElement in boxElements) {
            Box(
                modifier = boxModifier
                    .align(boxElement.alignment)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                boxElement.color,
                                darkenColor(
                                    boxElement.color,
                                    if (isSystemInDarkTheme()) 0.8f else 0.74f
                                ),
                            )
                        ),
                        shape = RoundedCornerShape(cornerRadius)
                    )
                    .clickable(onClick = boxElement.onClick)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp),
                    text = boxElement.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = -(10).dp),
                    painter = painterResource(boxElement.iconId),
                    contentDescription = null
                )
            }
        }
    }
}

data class BoxElement(
    val name: String,
    val color: Color,
    val alignment: Alignment,
    val iconId: Int,
    val onClick: () -> Unit = {}
)
