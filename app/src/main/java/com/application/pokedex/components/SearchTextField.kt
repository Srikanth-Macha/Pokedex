package com.application.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.application.pokedex.ui.theme.DarkTextField
import com.application.pokedex.ui.theme.LightTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    onClickOfGo: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = if (isSystemInDarkTheme()) DarkTextField else LightTextField,
                shape = RoundedCornerShape(12.dp)
            ),
        label = {
            Text(text = label, color = MaterialTheme.colorScheme.primary)
        },
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 10.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable(onClick = onClickOfGo),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go"
            )
        },
        keyboardActions = KeyboardActions(onDone = { onClickOfGo() }),
        singleLine = true,
    )
}