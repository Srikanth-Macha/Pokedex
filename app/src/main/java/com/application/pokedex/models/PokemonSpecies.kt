package com.application.pokedex.models

data class PokemonSpecies(
    val base_happiness: Int = 0,
    val capture_rate: Int = 0,
    val color: Color = Color(),
    val egg_groups: List<EggGroup> = emptyList(),
    val evolution_chain: EvolutionChain,
    val evolves_from_species: Any,
    val flavor_text_entries: List<FlavorTextEntry> = emptyList(),
    val form_descriptions: List<Any> = emptyList(),
    val forms_switchable: Boolean = false,
    val gender_rate: Int = 0,
    val genera: List<Genera> = emptyList(),
    val generation: Generation,
    val growth_rate: GrowthRate,
    val habitat: Habitat,
    val has_gender_differences: Boolean = false,
    val hatch_counter: Int = 0,
    val id: Int = 0,
    val is_baby: Boolean = false,
    val is_legendary: Boolean = false,
    val is_mythical: Boolean = false,
    val name: String,
    val names: List<Name> = emptyList(),
    val order: Int = 0,
    val pal_park_encounters: List<PalParkEncounter> = emptyList(),
    val pokedex_numbers: List<PokedexNumber> = emptyList(),
    val shape: Shape,
    val varieties: List<Variety> = emptyList()
) {
    data class Name(
        val language: Language,
        val name: String
    )

    data class Language(
        val name: String,
        val url: String
    )

    data class Shape(
        val name: String,
        val url: String
    )

    data class Variety(
        val is_default: Boolean,
        val pokemon: Pokemon.PokemonX
    )

    data class EvolutionChain(
        val url: String
    )

    data class EggGroup(
        val name: String,
        val url: String
    )

    data class Color(
        val name: String = "",
        val url: String = ""
    )

    data class Generation(
        val name: String,
        val url: String
    )

    data class FlavorTextEntry(
        val flavor_text: String,
        val language: Language,
        val version: VersionX
    )

    data class VersionX(
        val name: String,
        val url: String
    )

    data class Genera(
        val genus: String,
        val language: Language
    )

    data class GrowthRate(
        val name: String,
        val url: String
    )

    data class Habitat(
        val name: String,
        val url: String
    )

    data class PalParkEncounter(
        val area: Any,
        val base_score: Int,
        val rate: Int
    )

    data class PokedexNumber(
        val entry_number: Int,
        val pokedex: Pokedex
    )

    data class Pokedex(
        val name: String,
        val url: String
    )
}