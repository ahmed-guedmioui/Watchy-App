package com.ahmed_apps.watchy_course.details.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.details.presentation.components.FavoritesSection
import com.ahmed_apps.watchy_course.details.presentation.components.InfoSection
import com.ahmed_apps.watchy_course.details.presentation.components.OverViewSection
import com.ahmed_apps.watchy_course.details.presentation.components.PosterSection
import com.ahmed_apps.watchy_course.details.presentation.components.SimilarSection
import com.ahmed_apps.watchy_course.details.presentation.components.VideoSection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Ahmed Guedmioui
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    mainNavController: NavController,
    detailsNavController: NavController,
    detailsState: DetailsState,
    onEvent: (DetailsUiEvents) -> Unit
) {

    if (detailsState.media == null) {
        SomethingWentWrong()
    } else {

        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = refreshScope.launch {
            refreshing = true
            delay(1500)
            onEvent(DetailsUiEvents.Refresh)
            refreshing = false
        }

        val refreshState = rememberPullRefreshState(
            refreshing = refreshing,
            onRefresh = ::refresh
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    VideoSection(
                        media = detailsState.media,
                        onEvent = onEvent
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 180.dp)
                    ) {

                        PosterSection(
                            media = detailsState.media
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        InfoSection(
                            media = detailsState.media,
                            readableTime = detailsState.readableTime
                        )

                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                OverViewSection(
                    media = detailsState.media
                )

                SimilarSection(
                    mainNavController = mainNavController,
                    detailsNavController = detailsNavController,
                    media = detailsState.media,
                    detailsState = detailsState
                )

                Spacer(modifier = Modifier.height(100.dp))
            }

            FavoritesSection(
                detailsState = detailsState,
                onEvent = onEvent,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

            PullRefreshIndicator(
                refreshing = refreshing,
                state = refreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )

        }

    }

}


@Composable
fun SomethingWentWrong() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(R.string.something_went_wrong),
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}














