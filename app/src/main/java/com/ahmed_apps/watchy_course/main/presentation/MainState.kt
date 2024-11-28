package com.ahmed_apps.watchy_course.main.presentation

import com.ahmed_apps.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
data class MainState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,

    val trendingPage: Int = 1,
    val tvPage: Int = 1,
    val moviesPage: Int = 1,

    val trendingList: List<Media> = emptyList(),
    val tvList: List<Media> = emptyList(),
    val moviesList: List<Media> = emptyList(),

    // 2 from trending + 2 from tv + 2 from movies
    val specialList: List<Media> = emptyList(),

    val name: String = "Ahmed"

)




















