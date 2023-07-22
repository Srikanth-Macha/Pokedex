package com.application.pokedex.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import com.application.pokedex.models.Pokemon
import com.application.pokedex.ui.theme.AtkColor
import com.application.pokedex.ui.theme.DefColor
import com.application.pokedex.ui.theme.HPColor
import com.application.pokedex.ui.theme.SpAtkColor
import com.application.pokedex.ui.theme.SpDefColor
import com.application.pokedex.ui.theme.SpdColor
import com.application.pokedex.ui.theme.TypeBug
import com.application.pokedex.ui.theme.TypeDark
import com.application.pokedex.ui.theme.TypeDragon
import com.application.pokedex.ui.theme.TypeElectric
import com.application.pokedex.ui.theme.TypeFairy
import com.application.pokedex.ui.theme.TypeFighting
import com.application.pokedex.ui.theme.TypeFire
import com.application.pokedex.ui.theme.TypeFlying
import com.application.pokedex.ui.theme.TypeGhost
import com.application.pokedex.ui.theme.TypeGrass
import com.application.pokedex.ui.theme.TypeGround
import com.application.pokedex.ui.theme.TypeIce
import com.application.pokedex.ui.theme.TypeNormal
import com.application.pokedex.ui.theme.TypePoison
import com.application.pokedex.ui.theme.TypePsychic
import com.application.pokedex.ui.theme.TypeRock
import com.application.pokedex.ui.theme.TypeSteel
import com.application.pokedex.ui.theme.TypeWater
import java.util.Locale


fun getDominantColor(drawable: Drawable): Int {
    val bitmap =
        (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    return Palette.from(bitmap).generate().getDominantColor(Color.DarkGray.toArgb())
}


fun darkenColor(color: Color, amount: Float): Color {
    val red = (color.red * amount).coerceIn(0f, 1f)
    val green = (color.green * amount).coerceIn(0f, 1f)
    val blue = (color.blue * amount).coerceIn(0f, 1f)

    return Color(red, green, blue)
}

fun getPokemonTypeColor(type: Pokemon.Type) = when (type.type.name.lowercase(Locale.getDefault())) {
    "normal" -> TypeNormal
    "fire" -> TypeFire
    "water" -> TypeWater
    "electric" -> TypeElectric
    "grass" -> TypeGrass
    "ice" -> TypeIce
    "fighting" -> TypeFighting
    "poison" -> TypePoison
    "ground" -> TypeGround
    "flying" -> TypeFlying
    "psychic" -> TypePsychic
    "bug" -> TypeBug
    "rock" -> TypeRock
    "ghost" -> TypeGhost
    "dragon" -> TypeDragon
    "dark" -> TypeDark
    "steel" -> TypeSteel
    "fairy" -> TypeFairy
    else -> Color.Black
}


fun getPokemonStatColor(stat: Pokemon.Stat) = when (stat.stat.name.lowercase(Locale.getDefault())) {
    "hp" -> HPColor
    "attack" -> AtkColor
    "defense" -> DefColor
    "special-attack" -> SpAtkColor
    "special-defense" -> SpDefColor
    "speed" -> SpdColor
    else -> Color.White
}


fun getStatAbbreviation(statName: String) = when (statName.lowercase(Locale.getDefault())) {
    "hp" -> "HP"
    "attack" -> "Atk"
    "defense" -> "Def"
    "special-attack" -> "SpAtk"
    "special-defense" -> "SpDef"
    "speed" -> "Spd"
    else -> ""
}