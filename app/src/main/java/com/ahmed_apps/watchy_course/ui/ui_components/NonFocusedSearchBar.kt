package com.ahmed_apps.watchy_course.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.R

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun NonFocusedSearchBar(
    name: String = "",
    mainNavController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable {
//                    mainNavController.navigate()
                }
                .padding(start = 16.dp, end = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(R.string.search_a_movies_or_tv_series),
                tint = MaterialTheme.colorScheme.onBackground.copy(0.5f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.search_a_movies_or_tv_series),
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                modifier = Modifier.weight(1f)
            )

            if (name.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .alpha(0.8f)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable {
//                            mainNavController.navigate()
                        }
                ) {
                    Text(
                        text = name.take(1),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 19.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable {
//                            mainNavController.navigate()
                }
        ) {

            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Rounded.Bookmarks,
                contentDescription = stringResource(R.string.favorites_and_bookmarks),
                tint = MaterialTheme.colorScheme.primary
            )

        }

    }
}





















