package com.application.pokedex.screens.pokemons_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.application.pokedex.components.PokemonsListGrid
import com.application.pokedex.components.TopHeader
import com.application.pokedex.models.Result
import com.application.pokedex.screens.ErrorScreen
import com.application.pokedex.screens.destinations.PokemonsDetailsScreenDestination
import com.application.pokedex.util.Response
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PokemonsListScreen(
    navigator: DestinationsNavigator,
    pokemonNameToSearch: String? = null
) {
    val viewModel = hiltViewModel<PokemonsListViewModel>()
    val initialLoadingState by viewModel.initialLoadingState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopHeader(modifier = Modifier.align(Alignment.Start), headerText = pokemonNameToSearch) {
            navigator.popBackStack()
        }

        when (initialLoadingState) {
            is Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            is Response.Success -> {
                // If pokemonNameToSearch is not null, it should filter the list by pokemon name that is searched.
                if (pokemonNameToSearch != null) {
                    viewModel.filterPokemonsByName(pokemonNameToSearch)
                }

                // what to do when user clicks on a pokemon.
                val onPokemonClickLambda by remember {
                    mutableStateOf<(Result, Int) -> Unit>({ pokemonResult, _ ->
                        navigator.navigate(PokemonsDetailsScreenDestination(pokemonResult))
                    })
                }

                PokemonsListGrid(
                    viewModel = viewModel,
                    onPokemonClick = onPokemonClickLambda
                )
            }

            is Response.Error -> {
                ErrorScreen(initialLoadingState.error.toString())
            }
        }
    }
}