package com.ahmed_apps.watchy_course.search.peresentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.main.presentation.MainState
import com.ahmed_apps.watchy_course.main.presentation.MainUiEvents
import com.ahmed_apps.watchy_course.search.peresentation.components.SearchItem
import com.ahmed_apps.watchy_course.ui.theme.BigRadius
import com.ahmed_apps.watchy_course.ui.theme.HugeRadius
import com.ahmed_apps.watchy_course.ui.ui_components.FocusedTopBar
import com.ahmed_apps.watchy_course.ui.ui_components.MediaItemImageAndTitle
import com.ahmed_apps.watchy_course.ui.ui_components.NonFocusedTopBar
import com.ahmed_apps.watchy_course.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * @author Ahmed Guedmioui
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchListScreen(
    mainNavController: NavController
) {

    val searchViewModel = hiltViewModel<SearchViewModel>()
    val searchState by searchViewModel.searchState.collectAsState()

    val toolbarHeightPx = with(LocalDensity.current) {
        HugeRadius.roundToPx().toFloat()
    }

    val toolbarOffsetHeightPx = remember {
        mutableFloatStateOf(0f)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset, source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.floatValue + delta
                toolbarOffsetHeightPx.floatValue = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    LaunchedEffect(true) {
        searchViewModel.navigateToDetailsChannel.collectLatest { mediaId ->
            mainNavController.navigate(
                "${Screen.CoreDetails.route}?mediaId=${mediaId}"
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        val lazyState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(190.dp),
            state = lazyState,
            contentPadding = PaddingValues(top = BigRadius)
        ) {

            items(searchState.searchList.size) { index ->
                SearchItem(
                    media = searchState.searchList[index],
                    searchViewModel::onEvent
                )

                if (index >= searchState.searchList.size - 1 && !searchState.isLoading) {
                    searchViewModel.onEvent(SearchUiEvents.OnPaginate)
                }

            }

        }

        FocusedTopBar(
            toolbarOffsetHeightPx = toolbarOffsetHeightPx.floatValue.roundToInt(),
            searchState = searchState,
            searchViewModel::onEvent
        )

    }
}





















