package com.ahmed_apps.watchy_course.main.domain.model

/**
 * @author Ahmed Guedmioui
 */
data class Media(
    val mediaId: Int,

    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<String>,
    val mediaType: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    var category: String
)










