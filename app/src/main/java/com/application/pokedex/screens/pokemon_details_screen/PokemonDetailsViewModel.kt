package com.application.pokedex.screens.pokemon_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.pokedex.models.Pokemon
import com.application.pokedex.models.Result
import com.application.pokedex.repository.PokemonRepository
import com.application.pokedex.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonDetailsState =
        MutableStateFlow<Response<Pokemon>>(Response.Loading(Pokemon()))
    val pokemonDetailsState = _pokemonDetailsState.asStateFlow()

    fun getPokemonDetails(pokemonResult: Result) {
        viewModelScope.launch {
            _pokemonDetailsState.update { pokemonRepository.getPokemonDetails(pokemonResult.url) }
        }
    }
}