package com.ahmed_apps.watchy_course.main.presentation

/**
 * @author Ahmed Guedmioui
 */
sealed class MainUiEvents {

    data class Refresh(val route: String): MainUiEvents()
    data class Paginate(val route: String): MainUiEvents()

}