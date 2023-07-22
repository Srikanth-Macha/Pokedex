package com.application.pokedex.screens.pokemons_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.pokedex.models.Result
import com.application.pokedex.repository.PokemonRepository
import com.application.pokedex.screens.pokemons_list_screen.pagination.PaginatorImpl
import com.application.pokedex.screens.pokemons_list_screen.state.PokemonListScreenState
import com.application.pokedex.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {
    var state = MutableStateFlow(PokemonListScreenState())
    val pokemonsListState = MutableStateFlow<List<Result>>(emptyList())
    val initialLoadingState =
        MutableStateFlow<Response<Boolean>>(Response.Loading(state.value.isLoading))

    private val paginator = PaginatorImpl(
        initialKey = 0,
        onLoadUpdated = {
            state.value = state.value.copy(isLoading = it)
        },
        onRequest = {
            pokemonRepository.loadPokemons(state.value.page * 20)
        },
        getNextKey = {
            state.value.page + 1
        },
        onError = { error ->
            state.value = state.value.copy(error = error, isLoading = false)
            initialLoadingState.update { Response.Error(error.toString()) }
        },
        onSuccess = { newKey, pokemonResults ->

            if (pokemonResults == null) {
                return@PaginatorImpl
            }

            state.value = state.value.copy(
                isSuccess = true,
                isLoading = false,
                items = state.value.items + pokemonResults,
                page = newKey,
                endReached = pokemonResults.isEmpty()
            )

            pokemonsListState.update { state.value.items + pokemonResults }
            initialLoadingState.update { Response.Success(true) }
        },
    )

    init {
        loadNextPokemons()
    }

    fun loadNextPokemons() {
        viewModelScope.launch {
            paginator.loadMorePokemons()
        }
    }

    fun filterPokemonsByName(pokemonNameToSearch: String) {
        val filteredList = state.value.items.filter {
            val regex =
                Regex(Pattern.quote(pokemonNameToSearch), RegexOption.IGNORE_CASE)
            regex.containsMatchIn(it.name)
        }

        state.value = state.value.copy(items = filteredList)
        pokemonsListState.value = filteredList
    }
}