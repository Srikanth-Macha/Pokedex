package com.application.pokedex.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonList(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    var results: List<Result> = emptyList()
) : Parcelable