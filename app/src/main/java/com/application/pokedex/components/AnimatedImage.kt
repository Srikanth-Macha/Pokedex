package com.application.pokedex.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun AnimatedImage(
    modifier: Modifier = Modifier,
    model: String,
    onSuccess: (AsyncImagePainter.State.Success) -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(model)
            .size(Size.ORIGINAL)
            .build(),
        onSuccess = onSuccess
    )

    AnimatedVisibility(
        visible = painter.state is AsyncImagePainter.State.Success,
        enter = fadeIn() + expandIn(
            animationSpec = tween(durationMillis = 1000),
            expandFrom = Alignment.TopCenter
        )
    ) {
        Image(
            painter = painter,
            modifier = modifier,
            contentDescription = "Pokemon Image",
            contentScale = ContentScale.Crop
        )
    }
}