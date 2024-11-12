package com.ahmed_apps.watchy_course.details.presentation.details

import com.ahmed_apps.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
data class DetailsState(
    val media: Media? = null,
    val videoId: String = "",
    val readableTime: String = "",

    val videoList: List<String> = emptyList(),
    val similarList: List<Media> = emptyList()
)
























