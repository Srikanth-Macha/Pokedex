package com.application.pokedex.models


data class Pokemon(
    val abilities: List<Ability> = emptyList(),
    val base_experience: Int = 0,
    val forms: List<Form> = emptyList(),
    val game_indices: List<GameIndice> = emptyList(),
    val height: Int = 0,
    val held_items: List<HeldItem> = emptyList(),
    val id: Int = 0,
    val is_default: Boolean = false,
    val location_area_encounters: String = "",
    val moves: List<Move> = emptyList(),
    val name: String = "",
    val order: Int = 0,
    val past_types: List<Any> = emptyList(),
    val species: Species = Species(),
    val sprites: Sprites = Sprites(),
    val stats: List<Stat> = emptyList(),
    val types: List<Type> = emptyList(),
    val weight: Int = 0
) {
    data class Ability(
        val ability: AbilityX,
        val is_hidden: Boolean,
        val slot: Int
    )

    data class AbilityX(
        val name: String,
        val url: String
    )

    data class Form(
        val name: String,
        val url: String
    )

    data class GameIndice(
        val game_index: Int,
        val version: Version
    )

    data class Version(
        val name: String,
        val url: String
    )

    data class VersionDetail(
        val rarity: Int,
        val version: Version
    )

    data class HeldItem(
        val item: Item,
        val version_details: List<VersionDetail>
    )

    data class Item(
        val name: String,
        val url: String
    )

    data class Move(
        val move: MoveX,
        val version_group_details: List<VersionGroupDetail>
    )

    data class MoveX(
        val name: String,
        val url: String
    )

    data class VersionGroupDetail(
        val level_learned_at: Int,
        val move_learn_method: MoveLearnMethod,
        val version_group: VersionGroup
    )

    data class VersionGroup(
        val name: String,
        val url: String
    )

    data class MoveLearnMethod(
        val name: String,
        val url: String
    )

    data class Species(
        val name: String = "",
        val url: String = ""
    )

    data class Sprites(
        val back_default: String = "",
        val back_female: Any? = null,
        val back_shiny: String = "",
        val back_shiny_female: Any? = null,
        val front_default: String = "",
        val front_female: Any? = null,
        val front_shiny: String = "",
        val front_shiny_female: Any? = null,
        val other: Other? = null,
        val versions: Versions? = null
    )

    data class Stat(
        val base_stat: Int,
        val effort: Int,
        val stat: StatX
    )

    data class StatX(
        val name: String,
        val url: String
    )


    data class Type(
        val slot: Int,
        val type: TypeX
    )

    data class TypeX(
        val name: String,
        val url: String
    )

    data class Other(
        val dream_world: Any,
        val home: Any,
        val official_artwork: Any
    )

    data class PokemonX(
        val name: String,
        val url: String
    )
}

data class Versions(
    val generation_i: Any,
    val generation_ii: Any,
    val generation_iii: Any,
    val generation_iv: Any,
    val generation_v: Any,
    val generation_vi: Any,
    val generation_vii: Any,
    val generation_viii: Any
)