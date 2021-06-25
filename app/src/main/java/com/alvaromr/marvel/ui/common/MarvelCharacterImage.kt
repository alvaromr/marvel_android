package com.alvaromr.marvel.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alvaromr.marvel.R
import com.alvaromr.marvel.model.MarvelCharacter
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun MarvelCharacterImage(
    marvelCharacter: MarvelCharacter,
    modifier: Modifier = Modifier,
    small: Boolean = true,
) {
    val painter = rememberCoilPainter(
        request = marvelCharacter.thumbnailUrl,
    )

    val height = if (small) 75.dp else 150.dp
    val width = if (small) 50.dp else 100.dp

    Box(modifier) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .height(height)
                .width(width)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )

        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is ImageLoadState.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_broken_image),
                    "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
