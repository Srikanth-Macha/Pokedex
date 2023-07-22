package com.application.pokedex.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.application.pokedex.models.Result
import com.application.pokedex.screens.pokemons_list_screen.PokemonsListViewModel

@Composable
fun PokemonsListGrid(
    viewModel: PokemonsListViewModel,
    onPokemonClick: (Result, Int) -> Unit,
) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    val pokemonsListState by viewModel.pokemonsListState.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(150.dp),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(10.dp)
    ) {
        itemsIndexed(
            items = pokemonsListState,
            key = { index, _ -> index }
        ) { index, pokemonResult ->
            PokemonCard(
                index = index,
                onPokemonClick = onPokemonClick,
                pokemonResult = pokemonResult,
                viewModel = viewModel,
            )
        }

        if (screenState.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}