package com.application.pokedex.screens.pokemons_list_screen.pagination

interface Paginator<Key, Item> {
    suspend fun loadMorePokemons()

    fun reset()
}