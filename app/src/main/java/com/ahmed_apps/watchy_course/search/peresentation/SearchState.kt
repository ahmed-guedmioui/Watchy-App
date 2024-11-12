package com.ahmed_apps.watchy_course.search.peresentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.ahmed_apps.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
data class SearchState (
    val isLoading: Boolean = false,
    val searchPage: Int = 1,
    val searchQuery: String = "",
    val searchList: List<Media> = emptyList()
)





















