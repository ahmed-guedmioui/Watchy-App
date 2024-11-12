package com.ahmed_apps.watchy_course.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsState
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsUiEvents

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun FavoritesSection(
    detailsState: DetailsState,
    modifier: Modifier,
    onEvent: (DetailsUiEvents) -> Unit
) {

    detailsState.media?.let { media ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            FloatingActionButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(R.string.like)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            FloatingActionButton(
                modifier = Modifier.fillMaxWidth(1f),
                onClick = { /*TODO*/ }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = stringResource(R.string.like)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(R.string.bookmark)
                    )
                }

            }

        }
    }

}























