package com.ahmed_apps.watchy_course.details.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsScreen
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsUiEvents
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsViewModel
import com.ahmed_apps.watchy_course.details.presentation.similar_list.SimilarListScreen
import com.ahmed_apps.watchy_course.details.presentation.watch_video.WatchVideoScreen
import com.ahmed_apps.watchy_course.util.Screen

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun CoreDetailsScreen(
    mediaId: Int,
    mainNavController: NavController
) {

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState by detailsViewModel.detailsState.collectAsState()

    LaunchedEffect(true) {
        detailsViewModel.onEvent(
            DetailsUiEvents.StartLoading(mediaId)
        )
    }

    val detailsNavController = rememberNavController()

    NavHost(
        navController = detailsNavController,
        startDestination = Screen.Details.route
    ) {
        composable(Screen.Details.route) {
            DetailsScreen(
                mainNavController = mainNavController,
                detailsNavController = detailsNavController,
                detailsState = detailsState,
                onEvent = detailsViewModel::onEvent
            )
        }

        composable(Screen.WatchVideo.route) {
            WatchVideoScreen(
                lifecycleOwner = LocalLifecycleOwner.current,
                detailsState = detailsState
            )
        }

        composable(Screen.Similar.route) {
            SimilarListScreen(
                mainNavController = mainNavController,
                detailsState = detailsState
            )
        }
    }

    val context = LocalContext.current
    LaunchedEffect(true) {
        detailsViewModel.navigateToWatchVideoChannel
            .collect { navigate ->
                if (navigate) {
                    detailsNavController.navigate(Screen.WatchVideo.route)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(
                            R.string.no_video_is_available_at_the_moment
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}
























