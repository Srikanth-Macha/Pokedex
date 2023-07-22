package com.application.pokedex.services

import com.application.pokedex.models.Pokemon
import com.application.pokedex.models.PokemonList
import com.application.pokedex.models.PokemonSpecies
import com.application.pokedex.network.HttpRoutes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {
    @GET(HttpRoutes.POKEMONS_LIST)
    suspend fun loadNextPokemons(@Query("offset") offset: Int): PokemonList

    @GET
    suspend fun getPokemonDetails(@Url pokemonDetailsUrl: String): Pokemon

    @GET("${HttpRoutes.POKEMON_SPECIES}{pokemon_name}")
    suspend fun getPokemonSpeciesData(@Path("pokemon_name") pokemonName: String): PokemonSpecies

//    @GET
//    suspend fun getEvolutionDetails(@Url evolutionChainUrl: String): EvolutionData
}