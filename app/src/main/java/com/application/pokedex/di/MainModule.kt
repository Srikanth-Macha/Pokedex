package com.application.pokedex.di

import com.application.pokedex.network.HttpRoutes
import com.application.pokedex.repository.PokemonRepository
import com.application.pokedex.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun createClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getPokemonServiceImpl(client: Retrofit): PokemonService {
        return client.create(PokemonService::class.java)
    }

    @Singleton
    @Provides
    fun getPokemonRepository(service: PokemonService): PokemonRepository {
        return PokemonRepository(service)
    }
}
