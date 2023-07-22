package com.application.pokedex.screens.pokemons_list_screen.state

import com.application.pokedex.models.Result

data class PokemonListScreenState(
    val page: Int = 0,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val endReached: Boolean = false,
    val items: List<Result> = emptyList(),
    val error: String? = null,
)
