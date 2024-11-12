package com.ahmed_apps.watchy_course.main.presentation.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.main.presentation.MainState
import com.ahmed_apps.watchy_course.ui.theme.Radius
import com.ahmed_apps.watchy_course.ui.ui_components.MediaItemImage
import com.ahmed_apps.watchy_course.util.Screen

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun MediaHomeScreenSection(
    route: String,
    mainState: MainState,
    mainNavController: NavController
) {

    var mediaList = emptyList<Media>()
    var title = ""

    when (route) {
        Screen.Trending.route -> {
            mediaList = mainState.trendingList.take(10)
            title = stringResource(R.string.trending)
        }

        Screen.Tv.route -> {
            mediaList = mainState.tvList.take(10)
            title = stringResource(R.string.tv_series)
        }

        Screen.Movies.route -> {
            mediaList = mainState.moviesList.take(10)
            title = stringResource(R.string.movies)
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )

            if (mediaList.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.see_all),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        mainNavController.navigate(route)
                    }
                )
            }
        }

        if (mediaList.isEmpty()) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                repeat(6) {
                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(Radius))
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

            }
        } else {
            LazyRow {
                items(mediaList.size) { index ->

                    var paddingEnd = 0.dp
                    if (index == mediaList.size - 1) {
                        paddingEnd = 16.dp
                    }

                    MediaItemImage(
                        media = mediaList[index],
                        mainNavController = mainNavController,
                        modifier = Modifier
                            .height(200.dp)
                            .width(150.dp)
                            .padding(start = 16.dp, end = paddingEnd)
                    )
                }
            }
        }
    }

}


















