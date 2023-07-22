package com.application.pokedex.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.application.pokedex.R


@Composable
fun TopHeader(modifier: Modifier = Modifier, headerText: String? = null, onBackClick: () -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier = modifier.padding(start = 10.dp, bottom = 10.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 4.dp)
                .clickable(onClick = onBackClick),
            painter = painterResource(id = R.drawable.arrow_back_ios),
            contentDescription = "Back"
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            modifier = Modifier.padding(start = 3.dp),
            text = headerText ?: "Pokedex",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp),
        thickness = 2.dp,
        color = MaterialTheme.colorScheme.onBackground
    )
}