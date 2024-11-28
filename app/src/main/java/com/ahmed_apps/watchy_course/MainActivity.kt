package com.ahmed_apps.watchy_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmed_apps.watchy_course.details.presentation.CoreDetailsScreen
import com.ahmed_apps.watchy_course.main.presentation.MainScreen
import com.ahmed_apps.watchy_course.main.presentation.MainViewModel
import com.ahmed_apps.watchy_course.main.presentation.main_media_list.MainMediaListScreen
import com.ahmed_apps.watchy_course.ui.theme.WatchyCourseTheme
import com.ahmed_apps.watchy_course.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchyCourseTheme {
                BarColor()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val mainViewModel = hiltViewModel<MainViewModel>()
                    val mainState = mainViewModel.mainState.collectAsState().value

                    val mainNavController = rememberNavController()

                    NavHost(
                        navController = mainNavController,
                        startDestination = Screen.Main.route
                    ) {
                        composable(route = Screen.Main.route) {
                            MainScreen(
                                mainNavController = mainNavController,
                                mainState = mainState,
                                onEvent = mainViewModel::event
                            )
                        }

                        composable(route = Screen.Trending.route) {
                            MainMediaListScreen(
                                route = Screen.Trending.route,
                                mainNavController = mainNavController,
                                mainState = mainState,
                                onEvent = mainViewModel::event
                            )
                        }

                        composable(route = Screen.Tv.route) {
                            MainMediaListScreen(
                                route = Screen.Tv.route,
                                mainNavController = mainNavController,
                                mainState = mainState,
                                onEvent = mainViewModel::event
                            )
                        }

                        composable(route = Screen.Movies.route) {
                            MainMediaListScreen(
                                route = Screen.Movies.route,
                                mainNavController = mainNavController,
                                mainState = mainState,
                                onEvent = mainViewModel::event
                            )
                        }

                        composable(
                            route = "${Screen.CoreDetails.route}?mediaId={mediaId}",
                            arguments = listOf(
                                navArgument("mediaId") {
                                    type = NavType.IntType
                                }
                            )
                        ) {

                            val mediaId = it.arguments?.getInt(
                                "mediaId"
                            ) ?: 0

                            CoreDetailsScreen(
                                mediaId = mediaId,
                                mainNavController = mainNavController
                            )
                        }

                    }

                }
            }
        }
    }

    @Composable
    private fun BarColor() {
        val systemUiController = rememberSystemUiController()
        val color = MaterialTheme.colorScheme.background
        LaunchedEffect(color) {
            systemUiController.setSystemBarsColor(color)
        }
    }
}






















