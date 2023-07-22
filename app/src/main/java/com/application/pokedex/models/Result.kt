package com.application.pokedex.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val name: String,
    val url: String,
) : Parcelable {
    val itemNumber: Int
        get() = url.dropLast(1).takeLastWhile { it != '/' }.toInt()
}