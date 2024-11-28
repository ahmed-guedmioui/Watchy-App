package com.ahmed_apps.watchy_course.ui.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.ahmed_apps.watchy_course.main.data.remote.api.MediaApi
import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.ui.theme.Radius
import com.ahmed_apps.watchy_course.util.Screen

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun MediaItemImage(
    modifier: Modifier = Modifier,
    media: Media,
    isPoster: Boolean = true,
    mainNavController: NavController
) {

    val imageUri = if (isPoster) {
        "${MediaApi.IMAGE_BASE_URL}${media.posterPath}"
    } else {
        "${MediaApi.IMAGE_BASE_URL}${media.backdropPath}"
    }

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Radius))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .clickable {
                mainNavController.navigate(
                    "${Screen.CoreDetails.route}?mediaId=${media.mediaId}"
                )
            }
    ) {
        when (imageState) {
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = imageState.painter,
                    contentDescription = media.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )
            }

            else -> { // Error
                Icon(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = media.title,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }

}
















