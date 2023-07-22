package com.application.pokedex.screens.pokemon_evolutions_screen

import androidx.lifecycle.ViewModel
import com.application.pokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EvolutionViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
//    val evolutionData = MutableStateFlow<Response<EvolutionData>>(Response.Loading(EvolutionData()))
//
//    fun getPokemonEvolutionData(pokemonName: String) {
//        viewModelScope.launch {
//            evolutionData.value = withContext(Dispatchers.IO) {
//                pokemonRepository.getEvolutionDetails(pokemonName)
//            }
//        }
//    }
}