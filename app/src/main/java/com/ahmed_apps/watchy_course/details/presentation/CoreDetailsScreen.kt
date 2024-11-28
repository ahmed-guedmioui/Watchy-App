package com.ahmed_apps.watchy_course.details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsScreen
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsUiEvents
import com.ahmed_apps.watchy_course.details.presentation.details.DetailsViewModel
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
                detailsState =detailsState ,
                onEvent = detailsViewModel::onEvent
            )
        }

        composable(Screen.WatchVideo.route) {

        }

        composable(Screen.Similar.route) {

        }
    }

}
























