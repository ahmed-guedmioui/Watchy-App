package com.ahmed_apps.watchy_course.util

/**
 * @author Ahmed Guedmioui
 */
sealed class Screen(val route: String) {
    object Main: Screen("main")
    object Trending: Screen("trending")
    object Tv: Screen("tv")
    object Movies: Screen("movies")
}