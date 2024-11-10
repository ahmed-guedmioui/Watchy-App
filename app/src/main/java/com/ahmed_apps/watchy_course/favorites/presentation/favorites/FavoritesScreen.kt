package com.ahmed_apps.watchy_course.favorites.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.favorites.presentation.FavoritesState
import com.ahmed_apps.watchy_course.favorites.presentation.FavoritesUiEvents
import com.ahmed_apps.watchy_course.ui.theme.MediumRadius
import com.ahmed_apps.watchy_course.ui.theme.font
import com.ahmed_apps.watchy_course.ui.ui_components.AutoSwipeSection
import com.ahmed_apps.watchy_course.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Ahmed Guedmioui
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritesScreen(
    mainNavController: NavController,
    favoritesNavController: NavController,
    favoritesState: FavoritesState,
    onEvent: (FavoritesUiEvents) -> Unit
) {

    val scope = rememberCoroutineScope()
    var refreshing by remember {
        mutableStateOf(false)
    }

    fun onRefresh() = scope.launch {
        refreshing = true
        delay(1500)
        onEvent(FavoritesUiEvents.Refresh)
        refreshing = false
    }

    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = ::onRefresh
    )

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background( MaterialTheme.colorScheme.background)
                    .height(50.dp),
                text = stringResource(R.string.favorites_and_bookmarks),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .pullRefresh(refreshState)
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                if (favoritesState.likedList.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(R.string.favorites),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .height(220.dp)
                            .fillMaxWidth(0.9f)
                            .padding(top = 20.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(MediumRadius))
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .align(Alignment.CenterHorizontally)
                    )

                } else {
                    AutoSwipeSection(
                        title = stringResource(R.string.favorites),
                        mainNavController = mainNavController,
                        mediaList = favoritesState.likedList,
                        showSeeAll = true,
                        route = Screen.LikedList.route,
                        navController = favoritesNavController
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                if (favoritesState.bookmarksList.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(R.string.bookmarks),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .height(220.dp)
                            .fillMaxWidth(0.9f)
                            .padding(top = 20.dp, bottom = 12.dp)
                            .clip(RoundedCornerShape(MediumRadius))
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .align(Alignment.CenterHorizontally)
                    )

                } else {
                    AutoSwipeSection(
                        title = stringResource(R.string.bookmarks),
                        mainNavController = mainNavController,
                        mediaList = favoritesState.bookmarksList,
                        showSeeAll = true,
                        route = Screen.Bookmarked.route,
                        navController = favoritesNavController
                    )
                }

            }

            PullRefreshIndicator(
                refreshing = refreshing,
                state = refreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )

        }

    }

}





















