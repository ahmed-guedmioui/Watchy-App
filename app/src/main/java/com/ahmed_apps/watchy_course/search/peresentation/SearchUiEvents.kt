package com.ahmed_apps.watchy_course.search.peresentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.ahmed_apps.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
sealed class SearchUiEvents {
    data class OnSearchQueryChange (
        val searchQuery: String
    ): SearchUiEvents()

    data class OnSearchItemClick(
        val media: Media
    ): SearchUiEvents()

    object OnPaginate: SearchUiEvents()
}






















