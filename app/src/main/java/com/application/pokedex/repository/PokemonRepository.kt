package com.application.pokedex.repository

import com.application.pokedex.models.Pokemon
import com.application.pokedex.models.Result
import com.application.pokedex.services.PokemonService
import com.application.pokedex.util.Response
import javax.inject.Inject


class PokemonRepository @Inject constructor(
    private val pokemonServiceImpl: PokemonService
) {
    suspend fun loadPokemons(offset: Int): Response<List<Result>> {
        return try {
            val pokemonList = pokemonServiceImpl.loadNextPokemons(offset)
            Response.Success(pokemonList.results)
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }

    suspend fun getPokemonDetails(url: String): Response<Pokemon> {
        return try {
            val data = pokemonServiceImpl.getPokemonDetails(url)
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }

//    suspend fun getEvolutionDetails(pokemonName: String): Response<EvolutionData> {
//        return try {
//            val species = pokemonServiceImpl.getPokemonSpeciesData(pokemonName)
//            val evolutionData = pokemonServiceImpl.getEvolutionDetails(species.evolution_chain.url)
//
//            println(evolutionData.toString())
//            Response.Success(evolutionData)
//        } catch (e: Exception) {
//            Response.Error(e.message.toString())
//        }
//    }
}