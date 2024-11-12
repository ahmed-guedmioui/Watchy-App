package com.ahmed_apps.watchy_course.details.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun MovieImage(
    imageState: AsyncImagePainter.State,
    description: String,
    icon: ImageVector? = null
) {

    when (imageState) {
        is AsyncImagePainter.State.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        is AsyncImagePainter.State.Success -> {
            Image(
                painter = imageState.painter,
                contentDescription = description,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        else -> {
            icon?.let {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = it,
                        contentDescription = description,
                        tint = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                        modifier = Modifier.size(80.dp)
                    )
                }

            }
        }
    }

}























